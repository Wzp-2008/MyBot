package cn.wzpmc.mybot.entities.messages;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode
public class EssenceMessage {
    @JSONField(name="sender_id")
    private Long senderId;
    @JSONField(name="sender_nick")
    private String senderNick;
    @JSONField(name="sender_time")
    private Long senderTime;
    @JSONField(name="operator_id")
    private Long operatorId;
    @JSONField(name="operator_nick")
    private String operatorNick;
    @JSONField(name="operator_time")
    private Long operatorTime;
    @JSONField(name="message_id")
    private Long messageId;
}
