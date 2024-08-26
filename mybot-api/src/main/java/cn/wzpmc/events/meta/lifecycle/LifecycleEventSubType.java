package cn.wzpmc.events.meta.lifecycle;

/**
 * 生命周期事件子类型
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:28
 */
public enum LifecycleEventSubType {
    /**
     * OneBot被启用
     *
     * @since 2024/8/1 下午10:29 v0.0.2-dev
     */
    ENABLE,
    /**
     * OneBot被禁用
     *
     * @since 2024/8/1 下午10:29 v0.0.2-dev
     */
    DISABLE,
    /**
     * WebSocket连接成功
     *
     * @since 2024/8/1 下午10:29 v0.0.2-dev
     */
    CONNECT
}
