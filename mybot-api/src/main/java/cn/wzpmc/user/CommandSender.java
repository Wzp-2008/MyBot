package cn.wzpmc.user;

import cn.wzpmc.message.MessageComponent;
import cn.wzpmc.user.permission.Permissions;

/**
 * 命令发送者
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:32
 */
public interface CommandSender {
    /**
     * 发送消息
     *
     * @param messageComponent 消息组件
     * @author wzp
     * @since 2024/7/31 上午2:42 v0.0.1-dev
     */
    void sendMessage(MessageComponent messageComponent);

    /**
     * 获取用户ID
     *
     * @return 用户ID
     * @author wzp
     * @since 2024/8/23 21:43 v0.0.5-dev
     */
    Long getId();

    /**
     * 设置用户ID
     *
     * @param id 用户ID
     * @author wzp
     * @since 2024/8/23 21:43 v0.0.5-dev
     */
    void setId(Long id);

    /**
     * 获取用户名
     *
     * @return 用户名
     * @author wzp
     * @since 2024/8/23 21:43 v0.0.5-dev
     */
    String getName();

    /**
     * 设置用户名
     *
     * @param name 用户名
     * @author wzp
     * @since 2024/8/23 21:43 v0.0.5-dev
     */
    void setName(String name);

    /**
     * 获取用户权限
     *
     * @return 权限
     * @author wzp
     * @since 2024/8/23 21:43 v0.0.5-dev
     */
    Permissions getPermissions();

    /**
     * 设置用户权限
     *
     * @param permissions 用户权限
     * @author wzp
     * @since 2024/8/23 21:44 v0.0.5-dev
     */
    void setPermissions(Permissions permissions);

    /**
     * 指令发送者是否为管理员
     *
     * @return 是否为管理员
     * @author wzp
     * @since 2024/8/1 下午4:50 v0.0.2-dev
     */
    default boolean isAdmin() {
        return this.getPermissions().isAdmin();
    }

    /**
     * 检查指令发送者是否为完整权限管理员
     *
     * @return 是否为管理员
     * @author wzp
     * @since 2024/8/27 15:05 v1.0.2
     */
    default boolean isFullAdmin() {
        return this instanceof IBot || IBot.getInstance().isBotOp(this.getId());
    }
}
