package cn.wzpmc.mybot.entities.events.group.message;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/17 19:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupNoticeMessageEvent extends GroupMessageEvent {

    public GroupNoticeMessageEvent(JSONObject data) {
        super("GroupNoticeMessageEvent", data);
    }
}
