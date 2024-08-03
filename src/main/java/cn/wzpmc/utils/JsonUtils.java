package cn.wzpmc.utils;

import cn.wzpmc.api.events.Event;
import cn.wzpmc.api.events.message.MessageEvent;
import cn.wzpmc.api.events.meta.MetaEvent;
import cn.wzpmc.api.events.notice.NoticeEvent;
import cn.wzpmc.api.events.notice.notify.NotifyEvent;
import cn.wzpmc.api.events.request.RequestEvent;
import cn.wzpmc.api.message.StringMessage;
import cn.wzpmc.api.message.json.JsonMessage;
import cn.wzpmc.utils.json.event.*;
import cn.wzpmc.utils.json.message.JsonMessageReader;
import cn.wzpmc.utils.json.message.JsonMessageWriter;
import cn.wzpmc.utils.json.message.StringMessageReader;
import com.alibaba.fastjson2.JSON;

/**
 * JSON相关工具类
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午2:04
 */
public class JsonUtils {
    public static void initWriter() {
        JSON.register(JsonMessage.class, new JsonMessageWriter());
    }
    public static void initReader() {
        JSON.register(MessageEvent.class, new MessageEventReader());
        JSON.register(MetaEvent.class, new MetaEventReader());
        JSON.register(NotifyEvent.class, new NotifyNoticeEventReader());
        JSON.register(NoticeEvent.class, new NoticeEventReader());
        JSON.register(RequestEvent.class, new RequestEventReader());
        JSON.register(Event.class, new EventReader());
        JSON.register(JsonMessage.class, new JsonMessageReader());
        JSON.register(StringMessage.class, new StringMessageReader());
    }

}
