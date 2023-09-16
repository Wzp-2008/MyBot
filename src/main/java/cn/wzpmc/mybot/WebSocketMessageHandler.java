package cn.wzpmc.mybot;

import cn.wzpmc.mybot.entities.utils.EventIdentifier;
import cn.wzpmc.mybot.utils.CommandUtils;
import cn.wzpmc.mybot.utils.EventUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static cn.wzpmc.mybot.Main.bot;

/**
 * @author 33572
 * @version 1.0.0
 * websocket消息处理类
 */
public class WebSocketMessageHandler extends SimpleChannelInboundHandler<Object> {
    private final WebSocketClientHandshaker handshaker;
    public  WebSocketMessageHandler(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }
    private static final Logger log = LoggerFactory.getLogger("MyBot-Thread-Network");

    /**
     * 当websocket连接激活时调用
     * @param channelHandlerContext 通道
     */
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext){
        log.info("开始建立websocket连接");
        //获取连接通道
        Channel channel = channelHandlerContext.channel();
        //进行websocket握手
        this.handshaker.handshake(channel);
        log.info("握手指令发出");
    }

    /**
     * 当websocket连接出现错误时调用
     * @param context 通道
     * @param cause 错误
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        log.error(cause.getLocalizedMessage());
        cause.printStackTrace();
    }

    /**
     * 当websocket连接断开时调用
     * @param context 通道
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext context){
        log.error("和websocket服务器断开连接");
    }

    /**
     * 当通道数据处理完成时调用
     * @param context 通道
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext context){
        context.flush();
    }

    /**
     * 当通道收到数据时调用
     *
     * @param ctx 通道
     * @param msg 消息内容
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof FullHttpResponse) {
            if (!handshaker.isHandshakeComplete()) {
                handshaker.finishHandshake(ctx.channel(), (FullHttpResponse) msg);
                log.info("握手成功");
                log.info("成功建立websocket连接");
            }
        }
        if (msg instanceof TextWebSocketFrame) {
            TextWebSocketFrame text = (TextWebSocketFrame) msg;
            String json = text.text();
            JSONObject data = JSON.parseObject(json);
            if (data != null) {
                //事件类型
                EventIdentifier identifier = EventIdentifier.getInstanceFromJsonObject(data);
                String[] command = CommandUtils.parseCommand(data, identifier, bot);
                if (command == null) {
                    EventUtils.runEvent(identifier, data);
                } else {
                    CommandUtils.handlerCommand(bot, identifier, command, data, log);
                }
                log.debug("get JsonData = {}", data);
            }
        }
    }
}
