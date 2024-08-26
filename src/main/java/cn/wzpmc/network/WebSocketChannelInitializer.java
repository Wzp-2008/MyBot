package cn.wzpmc.network;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.RequiredArgsConstructor;

/**
 * WebSocket连接初始化
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午1:19
 */
@RequiredArgsConstructor
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {
    private final ChannelHandler handler;
    private final HandshakePacketHandler handshakePacketHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new HttpClientCodec());
        pipeline.addLast(new ChunkedWriteHandler());
        pipeline.addLast(new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(handshakePacketHandler);
        pipeline.addLast(handler);
    }
}
