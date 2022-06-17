package cn.wzpmc.mybot.entities.events.group.decreases;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupDeceasesKickEvent extends GroupDecreasesEvent {
    public GroupDeceasesKickEvent(JSONObject data) {
        super("GroupMemberDeceasesKickEvent", data);
    }
}
