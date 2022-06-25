package cn.wzpmc.mybot.entities.events.group.request;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GroupRequestEvent extends Event {
    private Long groupId;
    private Long userId;
    private String comment;
    private String flag;

    public GroupRequestEvent(String eventName, JSONObject data) {
        super(eventName, data);
        this.groupId = data.getLong("group_id");
        this.userId = data.getLong("user_id");
        this.comment = data.getString("comment");
        this.flag = data.getString("flag");
    }
}
