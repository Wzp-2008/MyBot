package cn.wzpmc.events.request;

import cn.wzpmc.events.request.group.GroupJoinRequestEvent;

/**
 * 请求事件子类型
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:09
 */
public enum RequestEventType {
    /**
     * 加好友事件
     *
     * @since 2024/8/1 下午10:15 v0.0.2-dev
     */
    FRIEND(FriendAddRequestEvent.class),
    /**
     * 申请加群事件
     *
     * @since 2024/8/1 下午10:15 v0.0.2-dev
     */
    GROUP(GroupJoinRequestEvent.class);
    public final Class<? extends RequestEvent> clazz;

    RequestEventType(Class<? extends RequestEvent> clazz) {
        this.clazz = clazz;
    }
}
