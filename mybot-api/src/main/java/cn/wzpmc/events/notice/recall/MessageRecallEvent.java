package cn.wzpmc.events.notice.recall;

import cn.wzpmc.events.notice.NoticeEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息撤回事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRecallEvent extends NoticeEvent {
    /**
     * 被撤回的用户ID
     *
     * @since 2024/8/1 下午9:49 v0.0.2-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
    /**
     * 被撤回的消息ID
     *
     * @since 2024/8/1 下午9:49 v0.0.2-dev
     */
    @JSONField(name = "message_id")
    private Long messageId;
}
