package cn.wzpmc.mybot.entities.events.other;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:25
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PokeMessageEvent extends Event {
    private Long senderId;
    private Long userId;
    private Long targetId;
    private Long groupId;

    public PokeMessageEvent(JSONObject data) {
        super("PokeMessageEvent", data);
        this.senderId = data.getLong("sender_id");
        this.userId = data.getLong("user_id");
        this.groupId = data.getLong("group_id");
        this.targetId = data.getLong("target_id");
    }

    public boolean isGroup() {
        return this.groupId != null;
    }
}
