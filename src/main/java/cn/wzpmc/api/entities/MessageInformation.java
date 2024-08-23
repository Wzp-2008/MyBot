package cn.wzpmc.api.entities;

import cn.wzpmc.api.events.message.MessageType;
import cn.wzpmc.api.message.MessageComponent;
import cn.wzpmc.api.user.IUser;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * 获取消息操作返回
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/17 23:08
 */
@Data
public class MessageInformation {
    /**
     * 发送时间
     * @since 2024/8/23 21:34 v0.0.5-dev
     */
    private Integer time;
    /**
     * 消息类型
     * @since 2024/8/23 21:34 v0.0.5-dev
     */
    private MessageType type;
    /**
     * 消息 ID
     * @since 2024/8/23 21:34 v0.0.5-dev
     */
    @JSONField(name = "message_id")
    private Integer messageId;
    /**
     * 消息真实 ID
     * @since 2024/8/23 21:34 v0.0.5-dev
     */
    @JSONField(name = "real_id")
    private Integer realId;
    /**
     * 发送人信息
     * @since 2024/8/23 21:35 v0.0.5-dev
     */
    private IUser sender;
    /**
     * 消息内容
     * @since 2024/8/23 21:35 v0.0.5-dev
     */
    private MessageComponent message;
}
