package cn.wzpmc.api.user;

import cn.wzpmc.api.message.MessageComponent;

/**
 * 消息发送者
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:32
 */
public interface CommandSender {
    /**
     * 获取用户ID
     * @author wzp
     * @since 2024/7/30 下午11:48 v0.0.1-dev
     * @return 用户ID
     */
    Long getId();

    /**
     * 获取用户名
     * @author wzp
     * @since 2024/7/30 下午11:48 v0.0.1-dev
     * @return 用户名
     */
    Long getName();
    /**
     * 发送消息
     * @author wzp
     * @since 2024/7/31 上午2:42 v0.0.1-dev
     * @param messageComponent 消息组件
     */
    void sendMessage(MessageComponent messageComponent);
}
