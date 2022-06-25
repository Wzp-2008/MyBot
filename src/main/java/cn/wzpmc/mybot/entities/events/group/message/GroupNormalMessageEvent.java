package cn.wzpmc.mybot.entities.events.group.message;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupNormalMessageEvent extends GroupMessageEvent {
    public GroupNormalMessageEvent(JSONObject data) {
        super("GroupMessageEvent", data);
    }
}
