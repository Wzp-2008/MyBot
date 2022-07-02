package cn.wzpmc.mybot.entities.events.group.message;

import cn.wzpmc.mybot.entities.infos.Anonymous;
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
public class GroupAnonymousMessageEvent extends GroupMessageEvent {
    private Anonymous anonymous;

    public GroupAnonymousMessageEvent(JSONObject data) {
        super("GroupAnonymousMessageEvent", data);
        this.anonymous = data.getJSONObject("anonymous").to(Anonymous.class);
    }
}
