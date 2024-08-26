package cn.wzpmc.events.request.group;

import cn.wzpmc.events.request.RequestEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 加群请求事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupJoinRequestEvent extends RequestEvent {
    /**
     * 加群子类型
     *
     * @since 2024/8/1 下午11:17 v0.0.2-dev
     */
    @JSONField(name = "sub_type")
    private GroupJoinRequestEventSubType subType;
    /**
     * 群ID
     *
     * @since 2024/8/1 下午11:17 v0.0.2-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
}
