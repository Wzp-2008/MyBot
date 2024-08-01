package cn.wzpmc.api.events.message.group;

import cn.wzpmc.api.events.message.MessageEvent;
import cn.wzpmc.api.user.group.GroupUser;
import cn.wzpmc.api.user.group.GroupUserAnonymousInfo;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群消息事件
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午8:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageEvent extends MessageEvent<GroupMessageSubType, GroupUser> {
    /**
     * 群号
     * @since 2024/8/1 下午11:11 v0.0.2-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * 匿名消息消息（当非匿名消息时为null）
     * @since 2024/8/1 下午11:10 v0.0.2-dev
     */
    private GroupUserAnonymousInfo anonymous;
}
