package cn.wzpmc.mybot;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;

/**
 * @author 33572
 * @version 1.0.0
 * netty连接初始化
 */
public class Initializer extends ChannelInitializer<SocketChannel> {
    private final WebSocketMessageHandler handler;
    public Initializer(WebSocketMessageHandler handler){
        this.handler = handler;
    }
    @Override
    protected void initChannel(SocketChannel channel){
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HttpClientCodec())
                .addLast(new HttpObjectAggregator(1024 * 1024))
                .addLast("handler",handler);
    }
}
