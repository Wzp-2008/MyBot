package cn.wzpmc.mybot.entities.events.group.request;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupAddRequestEvent extends GroupRequestEvent {
    public GroupAddRequestEvent(JSONObject data) {
        super("GroupAddRequestEvent", data);
    }
}
