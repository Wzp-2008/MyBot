package cn.wzpmc.api.events.notice.notify;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群内戳一戳事件
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:02
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PokeNotifyEvent extends NotifyEvent {
    /**
     * 被戳者ID
     * @since 2024/8/1 下午10:02 v0.0.2-dev
     */
    @JSONField(name = "target_id")
    private Long targetId;
}
