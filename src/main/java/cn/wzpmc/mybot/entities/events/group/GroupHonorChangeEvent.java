package cn.wzpmc.mybot.entities.events.group;

import cn.wzpmc.mybot.entities.events.Event;
import cn.wzpmc.mybot.enums.GroupHonorType;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupHonorChangeEvent extends Event {
    private Long groupId;
    private Long userId;
    private GroupHonorType honorType;

    public GroupHonorChangeEvent(JSONObject data) {
        super("GroupHonorChangeEvent", data);
        this.groupId = data.getLong("group_id");
        this.userId = data.getLong("user_id");
        this.honorType = GroupHonorType.valueOf(data.getString("honor_type"));
    }
}
