package cn.wzpmc.mybot.entities.events.friend;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FriendAddRequestEvent extends Event {
    private Long userId;
    private String comment;
    private String flag;

    public FriendAddRequestEvent(JSONObject data) {
        super("FriendAddRequestEvent", data);
        this.userId = data.getLong("user_id");
        this.comment = data.getString("comment");
        this.flag = data.getString("flag");
    }
}
