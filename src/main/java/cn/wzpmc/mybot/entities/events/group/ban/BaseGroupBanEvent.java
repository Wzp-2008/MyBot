package cn.wzpmc.mybot.entities.events.group.ban;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/17 23:37
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseGroupBanEvent extends Event {
    private Long groupId;
    private Long operatorId;
    private Long userId;
    private Long duration;

    public BaseGroupBanEvent(String eventName, JSONObject data) {
        super(eventName, data);
        this.groupId = data.getLong("group_id");
        this.operatorId = data.getLong("operator_id");
        this.userId = data.getLong("user_id");
        this.duration = data.getLong("duration");
    }
}
