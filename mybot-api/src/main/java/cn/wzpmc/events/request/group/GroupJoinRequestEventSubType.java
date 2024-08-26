package cn.wzpmc.events.request.group;

/**
 * 请求加群事件请求子类型
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:12
 */
public enum GroupJoinRequestEventSubType {
    /**
     * 加群请求
     *
     * @since 2024/8/1 下午10:12 v0.0.2-dev
     */
    ADD,
    /**
     * 邀请Bot加群
     *
     * @since 2024/8/1 下午10:12 v0.0.2-dev
     */
    INVITE
}
