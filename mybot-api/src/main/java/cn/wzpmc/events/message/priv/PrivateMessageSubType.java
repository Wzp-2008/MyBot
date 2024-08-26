package cn.wzpmc.events.message.priv;

/**
 * 私聊消息子类型
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午6:04
 */
public enum PrivateMessageSubType {
    /**
     * 正常消息
     *
     * @since 2024/8/1 下午6:07 v0.0.2-dev
     */
    NORMAL,
    /**
     * 匿名消息
     *
     * @since 2024/8/1 下午6:06 v0.0.2-dev
     */
    ANONYMOUS,
    /**
     * 系统提示
     *
     * @since 2024/8/1 下午6:06 v0.0.2-dev
     */
    NOTICE
}
