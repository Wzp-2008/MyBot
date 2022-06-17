package cn.wzpmc.mybot.entities.events.group.ban;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupLiftBanEvent extends BaseGroupBanEvent {

    public GroupLiftBanEvent(JSONObject data) {
        super("GroupBanEvent", data);
    }
}
