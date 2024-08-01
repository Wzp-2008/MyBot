package cn.wzpmc.api.events.notice;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群成员禁言事件
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupBanEvent extends NoticeEvent{
    /**
     * 群号
     * @since 2024/8/1 下午10:21 v0.0.2-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * 操作者用户ID
     * @since 2024/8/1 下午10:20 v0.0.2-dev
     */
    @JSONField(name = "operator_id")
    private Long operatorId;
    /**
     * 被禁言的用户ID
     * @since 2024/8/1 下午10:20 v0.0.2-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
    /**
     * 被禁言的时长(秒)
     * @since 2024/8/1 下午10:20 v0.0.2-dev
     */
    private Long duration;
}
