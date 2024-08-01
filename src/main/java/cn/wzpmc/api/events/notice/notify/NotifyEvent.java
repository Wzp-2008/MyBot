package cn.wzpmc.api.events.notice.notify;

import cn.wzpmc.api.events.notice.NoticeEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群提醒事件
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NotifyEvent extends NoticeEvent {
    /**
     * 群号
     * @since 2024/8/1 下午10:01 v0.0.2-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * 相关用户ID
     * @since 2024/8/1 下午10:00 v0.0.2-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
    /**
     * 群提醒事件子类型
     * @since 2024/8/1 下午11:26 v0.0.2-dev
     */
    @JSONField(name = "sub_type")
    private NotifySubType subType;
}
