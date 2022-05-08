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
public class GroupMemberDeceasesKickEvent extends Event{
    private Long time;
    private Long selfId;
    private Long groupId;
    private Long operatorId;
    private Long userId;
    public GroupMemberDeceasesKickEvent(JSONObject data){
        super("GroupMemberDeceasesKickEvent");
        this.time = data.getLong("time");
        this.selfId = data.getLong("selfId");
        this.groupId = data.getLong("operatorId");
        this.userId = data.getLong("user_id");
        this.operatorId = data.getLong("operator_id");
    }
}
