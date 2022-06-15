package cn.wzpmc.mybot.events;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupMemberIncreaseApproveEvent extends Event{
    private Long time;
    private Long selfId;
    private Long groupId;
    private Long operatorId;
    private Long userId;
    public GroupMemberIncreaseApproveEvent(JSONObject data){
        super("GroupMemberIncreaseEvent");
        this.time = data.getLong("time");
        this.groupId = data.getLong("group_id");
        this.selfId = data.getLong("self_id");
        this.operatorId = data.getLong("operator_id");
        this.userId = data.getLong("user_id");
    }
}
