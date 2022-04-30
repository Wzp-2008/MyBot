package cn.wzpmc.mybot;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * @author 33572
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
                .addLast("handler",handler);
    }
}
