package cn.wzpmc.events.notice.admin;

import cn.wzpmc.events.notice.NoticeEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群管理员更改事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupAdminChangeEvent extends NoticeEvent {
    /**
     * 事件子类型
     *
     * @since 2024/8/1 下午9:28 v0.0.2-dev
     */
    @JSONField(name = "sub_type")
    private GroupAdminChangeSubType subType;
    /**
     * 群号
     *
     * @since 2024/8/1 下午9:29 v0.0.2-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * 被操作者QQ号
     *
     * @since 2024/8/1 下午9:29 v0.0.2-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
}
