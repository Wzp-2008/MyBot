package cn.wzpmc.mybot.entities.events.privatemessage;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 7:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PrivateFriendMessageEvent extends PrivateMessageEvent {
    public PrivateFriendMessageEvent(JSONObject data) {
        super("PrivateFriendMessageEvent", data);
    }
}
