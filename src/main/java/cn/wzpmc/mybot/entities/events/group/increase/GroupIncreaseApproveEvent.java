package cn.wzpmc.mybot.entities.events.group.increase;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupIncreaseApproveEvent extends GroupIncreaseEvent {
    public GroupIncreaseApproveEvent(JSONObject data) {
        super("GroupMemberIncreaseEvent", data);
    }
}
