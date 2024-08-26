package cn.wzpmc.events.notice.recall;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群消息撤回事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageRecallEvent extends MessageRecallEvent {
    /**
     * 群号
     *
     * @since 2024/8/1 下午9:46 v0.0.2-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * 操作者ID
     *
     * @since 2024/8/1 下午9:45 v0.0.2-dev
     */
    @JSONField(name = "operator_id")
    private Long operatorId;
}
