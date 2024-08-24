package cn.wzpmc.api.user;

import cn.wzpmc.api.message.MessageComponent;
import cn.wzpmc.api.user.permission.Permissions;

/**
 * 命令发送者
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:32
 */
public interface CommandSender {
    /**
     * 发送消息
     * @author wzp
     * @since 2024/7/31 上午2:42 v0.0.1-dev
     * @param messageComponent 消息组件
     */
    void sendMessage(MessageComponent messageComponent);

    /**
     * 获取用户ID
     * @author wzp
     * @since 2024/8/23 21:43 v0.0.5-dev
     * @return 用户ID
     */
    Long getId();

    /**
     * 获取用户名
     * @author wzp
     * @since 2024/8/23 21:43 v0.0.5-dev
     * @return 用户名
     */
    String getName();

    /**
     * 获取用户权限
     * @author wzp
     * @since 2024/8/23 21:43 v0.0.5-dev
     * @return 权限
     */
    Permissions getPermissions();

    /**
     * 设置用户ID
     * @author wzp
     * @since 2024/8/23 21:43 v0.0.5-dev
     * @param id 用户ID
     */
    void setId(Long id);

    /**
     * 设置用户名
     * @author wzp
     * @since 2024/8/23 21:43 v0.0.5-dev
     * @param name 用户名
     */
    void setName(String name);

    /**
     * 设置用户权限
     * @author wzp
     * @since 2024/8/23 21:44 v0.0.5-dev
     * @param permissions 用户权限
     */
    void setPermissions(Permissions permissions);
    /**
     * 指令发送者是否为管理员
     * @author wzp
     * @since 2024/8/1 下午4:50 v0.0.2-dev
     * @return 是否为管理员
     */
    default boolean isAdmin(){
        return this.getPermissions().isAdmin();
    }
}
