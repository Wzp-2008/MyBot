package cn.wzpmc.network;

import cn.wzpmc.api.events.Event;
import com.alibaba.fastjson2.JSON;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.log4j.Log4j2;

/**
 * websocket包处理器
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午12:14
 */
@Log4j2
public class PacketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame webSocketFrame) {
        String text = webSocketFrame.text();
        if (!JSON.isValidObject(text)){
            log.warn("收到了无法处理的WebSocket数据包：{}", text);
            return;
        }
        System.out.println(text);
        Event event = JSON.parseObject(text, Event.class);
        System.out.println(event);
    }
}
