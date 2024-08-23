package cn.wzpmc.network;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.ActionResponse;
import cn.wzpmc.api.user.IBot;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 此类用于建立WebSocket连接
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/30 下午11:54
 */
@Log4j2
@RequiredArgsConstructor
public class WebSocketConnectionHandler {
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    private final IBot bot;
    private ChannelFuture channelFuture;
    private PacketHandler packetHandler;
    private HandshakePacketHandler handshakePacketHandler;
    /**
     * 建立连接
     * @author wzp
     * @since 2024/7/30 下午11:55 v0.0.1-dev
     * @param websocket websocket连接地址
     */
    public void connect(URI websocket){
        log.info("正在连接websocket");
        Bootstrap bootstrap = new Bootstrap();
        WebSocketClientHandshaker clientHandshaker = WebSocketClientHandshakerFactory.newHandshaker(websocket, WebSocketVersion.V13, null, false, new DefaultHttpHeaders());
        this.handshakePacketHandler = new HandshakePacketHandler(clientHandshaker);
        this.packetHandler = new PacketHandler(this.bot);
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new WebSocketChannelInitializer(this.packetHandler, this.handshakePacketHandler));
        this.channelFuture = bootstrap.connect(websocket.getHost(), websocket.getPort());
    }

    /**
     * 强制结束通信
     * @author wzp
     * @since 2024/7/31 上午2:04 v0.0.1-dev
     */
    public void kill(){
        log.info("结束连接...");
        this.eventLoopGroup.shutdownGracefully();
    }

    /**
     * 发送请求
     * @author wzp
     * @since 2024/8/23 21:49 v0.0.5-dev
     * @param request 请求
     * @return 返回
     * @param <REQUEST> 请求体类型
     * @param <RESPONSE> 返回类型
     * @throws InterruptedException 当请求进行时按下Ctrl+C时抛出
     */
    public <REQUEST, RESPONSE> ActionResponse<RESPONSE> sendRequest(Action<REQUEST, RESPONSE> request) throws InterruptedException {
        try {
            this.handshakePacketHandler.getHandshakeFuture().get();
        } catch (ExecutionException e) {
            log.error(e);
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
