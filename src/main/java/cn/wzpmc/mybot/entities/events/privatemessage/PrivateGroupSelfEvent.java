package cn.wzpmc.mybot.entities.events.privatemessage;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PrivateGroupSelfEvent extends PrivateMessageEvent {
    public PrivateGroupSelfEvent(JSONObject data) {
        super("PrivateGroupSelfEvent", data);
    }
}
