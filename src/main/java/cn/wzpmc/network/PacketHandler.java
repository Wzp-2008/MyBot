package cn.wzpmc.network;

import cn.wzpmc.api.events.Event;
import cn.wzpmc.api.user.IBot;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;

/**
 * websocket包处理器
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午12:14
 */
@Log4j2
@RequiredArgsConstructor
public class PacketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final IBot bot;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame webSocketFrame) {
        String text = webSocketFrame.text();
        if (!JSON.isValidObject(text)){
            log.warn("收到了无法处理的WebSocket数据包：{}", text);
            return;
        }
        JSONObject jsonObject = JSON.parseObject(text);
        if (jsonObject.containsKey("echo")) {
            handleApiEcho(jsonObject);
            return;
        }
        handleEvent(text);
    }
    private void handleEvent(String text){
        Event event = JSON.parseObject(text, Event.class);
        try {
            this.bot.triggerEvent(event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.error(new RuntimeException(e));
        }
    }
    private void handleApiEcho(JSONObject data){

    }
}
