package cn.wzpmc.network;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import lombok.extern.log4j.Log4j2;

import java.net.URI;

/**
 * 此类用于建立WebSocket连接
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/30 下午11:54
 */
@Log4j2
public class WebSocketConnectionHandler {
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    /**
     * 建立连接
     * @author wzp
     * @since 2024/7/30 下午11:55 v0.0.1-dev
     * @param websocket websocket连接地址
     * @return 一个ChannelFuture对象
     */
    public ChannelFuture connect(URI websocket){
        log.info("正在连接websocket");
        Bootstrap bootstrap = new Bootstrap();
        WebSocketClientHandshaker clientHandshaker = WebSocketClientHandshakerFactory.newHandshaker(websocket, WebSocketVersion.V13, null, false, new DefaultHttpHeaders());
        HandshakePacketHandler handshakePacketHandler = new HandshakePacketHandler(clientHandshaker);
        PacketHandler handler = new PacketHandler();
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new WebSocketChannelInitializer(handler, handshakePacketHandler));
        return bootstrap.connect(websocket.getHost(), websocket.getPort());
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
}
