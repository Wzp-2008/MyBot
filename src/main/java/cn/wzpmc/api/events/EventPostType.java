package cn.wzpmc.api.events;

import cn.wzpmc.api.events.message.MessageEvent;
import cn.wzpmc.api.events.meta.MetaEvent;
import cn.wzpmc.api.events.notice.NoticeEvent;
import cn.wzpmc.api.events.request.RequestEvent;

/**
 * 事件类型
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午5:47
 */
public enum EventPostType {
    /**
     * 消息事件
     * @since 2024/8/1 下午5:48 v0.0.2-dev
     */
    MESSAGE(MessageEvent.class),
    /**
     * 通知事件
     * @since 2024/8/1 下午5:47 v0.0.2-dev
     */
    NOTICE(NoticeEvent.class),
    /**
     * 请求事件
     * @since 2024/8/1 下午5:47 v0.0.2-dev
     */
    REQUEST(RequestEvent.class),
    /**
     * 元事件
     * @since 2024/8/1 下午5:47 v0.0.2-dev
     */
    META_EVENT(MetaEvent.class);
    public final Class<? extends Event> clazz;
    EventPostType(Class<? extends Event> clazz){
        this.clazz = clazz;
    }
}
