package cn.wzpmc.api.user;

import cn.wzpmc.api.message.MessageComponent;
import cn.wzpmc.api.user.permission.Permissions;

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

    /**
     * 获取指令发送者的权限
     * @author wzp
     * @since 2024/8/1 下午4:50 v0.0.2-dev
     * @return 权限
     */
    Permissions getPermission();

    /**
     * 指令发送者是否为管理员
     * @author wzp
     * @since 2024/8/1 下午4:50 v0.0.2-dev
     * @return 是否为管理员
     */
    default boolean isAdmin(){
        return Permissions.ADMIN.equals(this.getPermission());
    }
}
