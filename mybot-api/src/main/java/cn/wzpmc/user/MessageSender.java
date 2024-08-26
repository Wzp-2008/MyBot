package cn.wzpmc.user;

import cn.wzpmc.user.permission.Permissions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息发送者
 *
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/18 00:01
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageSender {
    /**
     * 用户ID
     *
     * @since 2024/7/30 下午11:48 v0.0.1-dev
     */
    @JSONField(name = "user_id")
    protected Long id;
    /**
     * 用户名
     *
     * @since 2024/7/30 下午11:48 v0.0.1-dev
     */
    @JSONField(name = "nickname")
    protected String name;
    /**
     * 权限
     *
     * @since 2024/8/1 下午8:24 v0.0.2-dev
     */
    protected Permissions permissions;
}
