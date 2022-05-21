package cn.wzpmc.mybot.Event;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 * @date 2022/5/20
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupUnBanEvent extends Event{
    @JSONField(name="time")
    private Long time;
    @JSONField(name="self_id")
    private Long selfId;
    @JSONField(name="group_id")
    private Long groupId;
    @JSONField(name="operator_id")
    private Long operatorId;
    @JSONField(name="user_id")
    private Long userId;
    @JSONField(name="duration")
    private Long duration;

    public GroupUnBanEvent(Long time, Long selfId, Long groupId, Long operatorId, Long userId, Long duration) {
        super("GroupBanEvent");
        this.time = time;
        this.selfId = selfId;
        this.groupId = groupId;
        this.operatorId = operatorId;
        this.userId = userId;
        this.duration = duration;
    }
}
