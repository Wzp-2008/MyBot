package cn.wzpmc.mybot.entities.events.group.essence;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:07
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupEssenceDeleteEvent extends GroupEssenceEvent {
    public GroupEssenceDeleteEvent(JSONObject data) {
        super("GroupEssenceDeleteEvent", data);
    }
}
