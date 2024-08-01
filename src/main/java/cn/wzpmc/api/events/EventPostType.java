package cn.wzpmc.api.events;

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
    MESSAGE,
    /**
     * 通知事件
     * @since 2024/8/1 下午5:47 v0.0.2-dev
     */
    NOTICE,
    /**
     * 请求事件
     * @since 2024/8/1 下午5:47 v0.0.2-dev
     */
    REQUEST,
    /**
     * 元事件
     * @since 2024/8/1 下午5:47 v0.0.2-dev
     */
    META_EVENT,
}
