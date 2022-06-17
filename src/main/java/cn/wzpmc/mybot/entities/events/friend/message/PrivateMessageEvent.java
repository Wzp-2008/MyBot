package cn.wzpmc.mybot.entities.events.friend.message;

import cn.wzpmc.mybot.entities.events.Event;
import cn.wzpmc.mybot.entities.messages.GroupMessage;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class PrivateMessageEvent extends Event {
    private GroupMessage message;

    public PrivateMessageEvent(JSONObject data) {
        super("GroupMessageEvent", data);
        this.message = new GroupMessage(data);
    }
}
