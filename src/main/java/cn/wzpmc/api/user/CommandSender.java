package cn.wzpmc.api.user;

import cn.wzpmc.api.message.MessageComponent;
import cn.wzpmc.api.user.permission.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息发送者
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:32
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class CommandSender {
    /**
     * 用户ID
     * @since 2024/7/30 下午11:48 v0.0.1-dev
     */
    protected Long id;
    /**
     * 用户名
     * @since 2024/7/30 下午11:48 v0.0.1-dev
     */
    protected String name;
    /**
     * 权限
     * @since 2024/8/1 下午8:24 v0.0.2-dev
     */
    protected Permissions permissions;
    /**
     * 发送消息
     * @author wzp
     * @since 2024/7/31 上午2:42 v0.0.1-dev
     * @param messageComponent 消息组件
     */
    public abstract void sendMessage(MessageComponent messageComponent);

    /**
     * 指令发送者是否为管理员
     * @author wzp
     * @since 2024/8/1 下午4:50 v0.0.2-dev
     * @return 是否为管理员
     */
    public boolean isAdmin(){
        return Permissions.ADMIN.equals(this.permissions);
    }
}
