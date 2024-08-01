package cn.wzpmc.api.events.notice.notify;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 红包幸运王事件
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LuckyKingNotifyEvent extends NotifyEvent {
    /**
     * 运气王ID
     * @since 2024/8/1 下午10:04 v0.0.2-dev
     */
    @JSONField(name = "target_id")
    private Long targetId;
}
