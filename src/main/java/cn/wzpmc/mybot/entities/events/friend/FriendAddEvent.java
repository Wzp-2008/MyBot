package cn.wzpmc.mybot.entities.events.friend;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FriendAddEvent extends Event {
    private Long userId;

    public FriendAddEvent(JSONObject data) {
        super("FriendAddEvent", data);
        this.userId = data.getLong("user_id");
    }
}
