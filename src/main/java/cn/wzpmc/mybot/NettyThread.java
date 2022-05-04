package cn.wzpmc.mybot;

import com.alibaba.fastjson.JSON;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author wzp
 * @date 2022/5/4
 * @version 1.0.0
 */
public class NettyThread extends Thread{
    private final Logger log = LoggerFactory.getLogger("MyBot-NettyThread");
    private final String ws;
    private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    public NettyThread(String ws){
        this.ws = ws;
    }
    @Override
    public void run() {
        try{
            URI uri = new URI(ws);
            Bootstrap bootstrap = new Bootstrap();
            WebSocketClientHandshaker webSocketClientHandshaker = WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders());
            WebSocketMessageHandler handler = new WebSocketMessageHandler(webSocketClientHandshaker);
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    .handler(new Initializer(handler));
            log.info("启动成功！");
            Channel channel = bootstrap.connect(uri.getHost(),uri.getPort()).sync().channel();
            channel.closeFuture().sync();
        } catch (URISyntaxException e) {
            log.error("URI转换失败，请确认你的websocket连接uri有效");
        } catch (InterruptedException e) {
            log.info("检测到Ctrl+C，退出");
        }finally {
            stopNetty();
        }
    }
    public void stopNetty(){
        log.info("NettyWebsocket连接终止");
        eventLoopGroup.shutdownGracefully();
    }
}
