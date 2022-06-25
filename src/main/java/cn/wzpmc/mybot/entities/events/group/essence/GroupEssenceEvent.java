package cn.wzpmc.mybot.entities.events.group.essence;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GroupEssenceEvent extends Event {
    private Long senderId;
    private Long operatorId;
    private Integer messageId;

    public GroupEssenceEvent(String eventName, JSONObject data) {
        super(eventName, data);
        this.senderId = data.getLong("sender_id");
        this.operatorId = data.getLong("operator_id");
        this.messageId = data.getInteger("message_id");
    }
}
