package cn.wzpmc.events.notice;

import cn.wzpmc.events.notice.admin.GroupAdminChangeEvent;
import cn.wzpmc.events.notice.essence.EssenceEvent;
import cn.wzpmc.events.notice.file.GroupFileUploadedEvent;
import cn.wzpmc.events.notice.notify.NotifyEvent;
import cn.wzpmc.events.notice.recall.GroupMessageRecallEvent;
import cn.wzpmc.events.notice.recall.MessageRecallEvent;
import cn.wzpmc.events.notice.user.decrease.GroupUserDecreaseEvent;
import cn.wzpmc.events.notice.user.increase.GroupUserIncreaseEvent;

/**
 * 通知类型
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:12
 */
public enum NoticeType {
    /**
     * 群文件上传事件
     *
     * @since 2024/8/1 下午10:24 v0.0.2-dev
     */
    GROUP_UPLOAD(GroupFileUploadedEvent.class),
    /**
     * 群管理变更事件
     *
     * @since 2024/8/1 下午10:24 v0.0.2-dev
     */
    GROUP_ADMIN(GroupAdminChangeEvent.class),
    /**
     * 用户退群事件
     *
     * @since 2024/8/1 下午10:24 v0.0.2-dev
     */
    GROUP_DECREASE(GroupUserDecreaseEvent.class),
    /**
     * 用户加群事件
     *
     * @since 2024/8/1 下午10:23 v0.0.2-dev
     */
    GROUP_INCREASE(GroupUserIncreaseEvent.class),
    /**
     * 群组禁言事件
     *
     * @since 2024/8/1 下午10:23 v0.0.2-dev
     */
    GROUP_BAN(GroupBanEvent.class),
    /**
     * 好友添加事件
     *
     * @since 2024/8/1 下午10:23 v0.0.2-dev
     */
    FRIEND_ADD(FriendAddEvent.class),
    /**
     * 群消息撤回事件
     *
     * @since 2024/8/1 下午10:23 v0.0.2-dev
     */
    GROUP_RECALL(GroupMessageRecallEvent.class),
    /**
     * 好友消息撤回事件
     *
     * @since 2024/8/1 下午10:23 v0.0.2-dev
     */
    FRIEND_RECALL(MessageRecallEvent.class),
    /**
     * 群提醒事件
     *
     * @see NotifyEvent
     * @since 2024/8/1 下午10:22 v0.0.2-dev
     */
    NOTIFY(NotifyEvent.class),
    /**
     * 精华消息事件
     *
     * @since 2024/9/16 21:25 v1.0.3
     */
    ESSENCE(EssenceEvent.class);
    public final Class<? extends NoticeEvent> clazz;

    NoticeType(Class<? extends NoticeEvent> clazz) {
        this.clazz = clazz;
    }
}
