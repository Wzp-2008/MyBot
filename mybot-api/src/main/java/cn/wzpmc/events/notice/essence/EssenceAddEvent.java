package cn.wzpmc.events.notice.essence;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 添加精华消息事件
 *
 * @author wzp
 * @version 1.0.3
 * @since 2024/9/16 21:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EssenceAddEvent extends EssenceEvent {
    /**
     * 群ID
     *
     * @since 2024/9/16 21:33 v1.0.3
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * 设置人ID
     *
     * @since 2024/9/16 21:33 v1.0.3
     */
    @JSONField(name = "user_id")
    private Long userId;
    /**
     * 消息ID
     *
     * @since 2024/9/16 21:33 v1.0.3
     */
    @JSONField(name = "message_id")
    private Long messageId;
    /**
     * 消息发送者ID
     *
     * @since 2024/9/16 21:33 v1.0.3
     */
    @JSONField(name = "sender_id")
    private Long senderId;
}
