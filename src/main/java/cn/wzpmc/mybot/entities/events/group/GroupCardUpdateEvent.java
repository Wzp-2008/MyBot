package cn.wzpmc.mybot.entities.events.group;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupCardUpdateEvent extends Event {
    private Long groupId;
    private Long userId;
    private String cardNew;
    private String cardOld;

    public GroupCardUpdateEvent(JSONObject data) {
        super("GroupCardUpdateEvent", data);
        this.groupId = data.getLong("group_id");
        this.userId = data.getLong("user_id");
        this.cardNew = data.getString("card_new");
        this.cardOld = data.getString("card_old");
    }
}
