package cn.wzpmc.mybot.entities.events.group.admin;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupAdminUnSetEvent extends GroupAdminEvent {
    public GroupAdminUnSetEvent(JSONObject data) {
        super("GroupAdminUnSetEvent", data);
    }
}
