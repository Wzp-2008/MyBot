package cn.wzpmc.mybot.events;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupAdminUnSetEvent extends Event{
    private Long time;
    private Long selfId;
    private Long groupId;
    private Long userId;
    public GroupAdminUnSetEvent(JSONObject data){
        super("GroupAdminUnSetEvent");
        this.groupId = data.getLong("group_id");
        this.selfId = data.getLong("selfId");
        this.time = data.getLong("time");
        this.userId = data.getLong("user_id");
    }
}
