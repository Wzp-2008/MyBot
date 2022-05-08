package cn.wzpmc.mybot.Event;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 * @date 2022/5/8
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupAdminSetEvent extends Event{
    private Long time;
    private Long selfId;
    private Long groupId;
    private Long userId;
    public GroupAdminSetEvent(JSONObject data){
        super("GroupAdminSetEvent");
        this.time = data.getLong("time");
        this.selfId = data.getLong("self_id");
        this.groupId = data.getLong("group_id");
        this.userId = data.getLong("user_id");
    }
}
