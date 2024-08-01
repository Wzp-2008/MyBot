package cn.wzpmc.network;

import cn.wzpmc.api.events.Event;
import cn.wzpmc.api.events.message.MessageEvent;
import cn.wzpmc.api.events.meta.MetaEvent;
import cn.wzpmc.api.events.notice.NoticeEvent;
import cn.wzpmc.api.events.notice.NoticeType;
import cn.wzpmc.api.events.notice.notify.NotifyEvent;
import cn.wzpmc.api.events.request.RequestEvent;
import cn.wzpmc.api.events.request.RequestEventType;
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
        Class<? extends Event> eventClass = switch (event.getPostType()) {
            case NOTICE -> {
                NoticeEvent noticeEvent = JSON.parseObject(text, NoticeEvent.class);
                NoticeType noticeType = noticeEvent.getNoticeType();
                if (NoticeType.NOTIFY.equals(noticeType)) {
                    NotifyEvent notifyEvent = JSON.parseObject(text, NotifyEvent.class);
                    yield notifyEvent.getSubType().clazz;
                }
                yield noticeType.clazz;
            }
            case MESSAGE -> {
                MessageEvent<?, ?> messageEvent = JSON.parseObject(text, MessageEvent.class);
                yield messageEvent.getMessageType().clazz;
            }
            case REQUEST -> {
                RequestEvent requestEvent = JSON.parseObject(text, RequestEvent.class);
                RequestEventType requestType = requestEvent.getRequestType();
                yield requestType.clazz;
            }
            case META_EVENT -> {
                MetaEvent metaEvent = JSON.parseObject(text, MetaEvent.class);
                yield metaEvent.getMetaEventType().clazz;
            }
        };
        Event event1 = JSON.parseObject(text, eventClass);
        System.out.println(event1);
    }
}
