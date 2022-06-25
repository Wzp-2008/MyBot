package cn.wzpmc.mybot.entities.events.group.file;

import cn.wzpmc.mybot.entities.events.Event;
import cn.wzpmc.mybot.entities.groupfiles.GroupFileObject;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupFileUploadEvent extends Event {
    private Long groupId;
    private Long userId;
    private GroupFileObject file;

    public GroupFileUploadEvent(JSONObject data) {
        super("GroupFileUploadEvent", data);
        this.groupId = data.getLong("group_id");
        this.userId = data.getLong("user_id");
        this.file = data.getJSONObject("file").to(GroupFileObject.class);
    }
}
