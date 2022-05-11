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
public class GroupMemberDeceasesKickMeEvent extends Event{
    private Long time;
    private Long selfId;
    private Long groupId;
    private Long operatorId;
    private Long userId;
    public GroupMemberDeceasesKickMeEvent(JSONObject data){
        super("GroupMemberDecreasesEvent");
        this.time = data.getLong("time");
        this.selfId = data.getLong("self_id");
        this.groupId = data.getLong("group_id");
        this.operatorId = data.getLong("operatorId");
        this.userId = data.getLong("user_id");
    }
}
