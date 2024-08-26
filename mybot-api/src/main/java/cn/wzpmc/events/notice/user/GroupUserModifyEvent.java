package cn.wzpmc.events.notice.user;

import cn.wzpmc.events.notice.NoticeEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群成员更改事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupUserModifyEvent<S> extends NoticeEvent {
    /**
     * 消息子类型
     *
     * @since 2024/8/1 下午9:31 v0.0.2-dev
     */
    @JSONField(name = "sub_type")
    private S subType;
    /***
     * 群ID
     * @since 2024/8/1 下午9:31 v0.0.2-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * 操作者
     *
     * @since 2024/8/1 下午9:32 v0.0.2-dev
     */
    @JSONField(name = "operator_id")
    private Long operatorId;
    /**
     * 加入/退出用户ID
     *
     * @since 2024/8/1 下午9:35 v0.0.2-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
}
