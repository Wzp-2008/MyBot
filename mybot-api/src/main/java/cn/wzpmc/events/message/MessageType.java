package cn.wzpmc.events.message;

import cn.wzpmc.events.message.group.GroupMessageEvent;
import cn.wzpmc.events.message.priv.PrivateMessageEvent;

/**
 * 消息类型
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午5:54
 */
public enum MessageType {
    /**
     * 私聊
     *
     * @since 2024/8/1 下午5:55 v0.0.2-dev
     */
    PRIVATE(PrivateMessageEvent.class),
    /**
     * 群
     *
     * @since 2024/8/1 下午5:55 v0.0.2-dev
     */
    GROUP(GroupMessageEvent.class);
    public final Class<? extends MessageEvent<?, ?>> clazz;

    MessageType(Class<? extends MessageEvent<?, ?>> clazz) {
        this.clazz = clazz;
    }
}
