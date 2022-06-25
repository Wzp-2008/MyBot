package cn.wzpmc.mybot.entities.events.group;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupRecallEvent extends Event {
    private Long groupId;
    private Long userId;
    private Long operatorId;
    private Long messageId;

    public GroupRecallEvent(JSONObject data) {
        super("GroupRecallEvent", data);
        this.groupId = data.getLong("group_id");
        this.userId = data.getLong("user_id");
        this.operatorId = data.getLong("operator_id");
        this.messageId = data.getLong("message_id");
    }
}
