package cn.wzpmc.api.api.actions.message.send;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * 发送私聊消息返回
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/17 21:55
 */
@Data
public class SendMessageActionResponseData {
    /**
     * 消息 ID
     * @since 2024/8/17 22:51 v0.0.5-dev
     */
    @JSONField(name = "message_id")
    private int messageId;
}
