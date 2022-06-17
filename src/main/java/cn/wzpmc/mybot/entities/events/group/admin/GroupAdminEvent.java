package cn.wzpmc.mybot.entities.events.group.admin;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/17 23:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GroupAdminEvent extends Event {
    private Long groupId;
    private Long userId;

    public GroupAdminEvent(String eventName, JSONObject data) {
        super(eventName, data);
        this.groupId = data.getLong("group_id");
        this.userId = data.getLong("user_id");
    }
}
