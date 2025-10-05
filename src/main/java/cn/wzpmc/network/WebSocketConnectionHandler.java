package cn.wzpmc.network;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.ActionResponse;
import cn.wzpmc.console.MyBotConsole;
import cn.wzpmc.entities.api.ApiResponseRequired;
import cn.wzpmc.entities.user.bot.MyBot;
import cn.wzpmc.plugins.configuration.INetworkConfiguration;
import cn.wzpmc.utils.json.action.ActionReader;
import com.alibaba.fastjson2.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.net.URI;
import java.util.concurrent.*;

/**
 * 此类用于建立WebSocket连接
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/30 下午11:54
 */
@Log4j2
@RequiredArgsConstructor
public class WebSocketConnectionHandler {
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private final ScheduledExecutorService connectionScheduler = Executors.newSingleThreadScheduledExecutor();
    private ScheduledFuture<?> resetScheduledFuture;
    private final MyBot bot;
    /**
     * websocket连接地址
     * @since 2025/3/26 17:56 v1.0.7
     */
    private final URI websocket;
    private ChannelFuture channelFuture;
    private PacketHandler packetHandler;
    private HandshakePacketHandler handshakePacketHandler;
    private int currentRetryCount = 0;


    private void tryReconnect() {
        if (this.resetScheduledFuture != null) {
            this.resetScheduledFuture.cancel(false);
        }
        INetworkConfiguration network = bot.getConfiguration().getNetwork();
        if (!network.isRetry()) {
            this.quit();
            return;
        }
        Integer maxRetryCount = network.getMaxRetryCount();
        if (maxRetryCount != 0 && currentRetryCount >= maxRetryCount) {
            this.quit();
            return;
        }
        this.currentRetryCount++;
        try {
            Thread.sleep(network.getRetryInterval());
        } catch (InterruptedException e) {
            this.quit();
            return;
        }
        log.info("尝试重连第{}次", currentRetryCount);
        this.connect();
    }

    private void quit() {
        for (ApiResponseRequired<?, ?> value : ActionReader.tasks.values()) {
            log.debug("response required {} exit...", value);
            value.getFuture().obtrudeException(new InterruptedException());
            log.debug("response required {} exited", value);
        }
        log.debug("handshaker exit...");
        this.handshakePacketHandler.getHandshakeFuture().obtrudeException(new InterruptedException());
        log.debug("handshaker exited");
        MyBotConsole console = bot.getConsole();
        log.debug("try shutdown bot");
        bot.setShutdown(true);
        if (console == null) {
            log.debug("try shutdown eventLoop");
            this.eventLoopGroup.shutdownGracefully();
            log.debug("eventLoop exited");
            System.exit(0);
        }
        log.debug("try shutdown console");
        console.shutdown();
        log.debug("console exited");
        System.exit(0);
    }

    /**
     * 建立连接
     * @author wzp
     * @since 2024/7/30 下午11:55 v0.0.1-dev
     */
    public void connect() {
        log.info("正在连接websocket");
        Bootstrap bootstrap = new Bootstrap();
        WebSocketClientHandshaker clientHandshaker = WebSocketClientHandshakerFactory.newHandshaker(websocket, WebSocketVersion.V13, null, false, new DefaultHttpHeaders(), 65536 * 100);
        this.handshakePacketHandler = new HandshakePacketHandler(clientHandshaker);
        this.packetHandler = new PacketHandler(this.bot, this::tryReconnect);
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new WebSocketChannelInitializer(this.packetHandler, this.handshakePacketHandler));
        this.channelFuture = bootstrap.connect(websocket.getHost(), websocket.getPort());
        this.channelFuture.addListener(future -> {
            if (!future.isSuccess()) {
                log.info("连接失败！");
                this.tryReconnect();
            } else {
                log.info("连接成功！");
                this.resetScheduledFuture = connectionScheduler.schedule(() -> {
                    this.currentRetryCount = 0;
                    this.resetScheduledFuture = null;
                }, 3, TimeUnit.SECONDS);
            }
        });
    }

    /**
     * 强制结束通信
     *
     * @author wzp
     * @since 2024/7/31 上午2:04 v0.0.1-dev
     */
    public void kill() {
        log.info("结束连接...");
        this.eventLoopGroup.shutdownGracefully();
    }

    /**
     * 发送请求
     *
     * @param request    请求
     * @param <REQUEST>  请求体类型
     * @param <RESPONSE> 返回类型
     * @return 返回
     * @throws InterruptedException 当请求进行时按下Ctrl+C时抛出
     * @author wzp
     * @since 2024/8/23 21:49 v0.0.5-dev
     */
    public <REQUEST, RESPONSE> ActionResponse<RESPONSE> sendRequest(Action<REQUEST, RESPONSE> request) throws InterruptedException {
        try {
            this.handshakePacketHandler.getHandshakeFuture().get();
        } catch (ExecutionException e) {
            log.error(e);
            return null;
        }
        CompletableFuture<ActionResponse<RESPONSE>> responsePromise = new CompletableFuture<>();
        packetHandler.registerResponse(request.getEcho(), responsePromise, request);
        channelFuture.channel().writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(request)));
        try {
            return responsePromise.get();
        } catch (ExecutionException e) {
            log.error(e);
        }
        return null;
    }
}
