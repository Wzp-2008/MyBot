package cn.wzpmc.mybot.entities.events.group.increase;

import cn.wzpmc.mybot.entities.events.group.decreases.GroupDecreasesEvent;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/17 23:27
 */
public class GroupIncreaseInviteEvent extends GroupDecreasesEvent {
    public GroupIncreaseInviteEvent(JSONObject data) {
        super("GroupIncreaseInviteEvent", data);
    }
}
