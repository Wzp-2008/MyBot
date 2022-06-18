package cn.wzpmc.mybot.entities.events.friend;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FriendRecallEvent extends Event {
    private Long userId;
    private Long messageId;

    public FriendRecallEvent(JSONObject data) {
        super("FriendRecallEvent", data);
        this.userId = data.getLong("user_id");
        this.messageId = data.getLong("message_id");
    }
}
