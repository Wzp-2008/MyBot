package cn.wzpmc.events.notice.notify;

import cn.wzpmc.events.notice.notify.honor.HonorNotifyEvent;

/**
 * 群提醒事件子类型
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:58
 */
public enum NotifySubType {
    /**
     * 群内戳一戳事件
     *
     * @since 2024/8/1 下午10:16 v0.0.2-dev
     */
    POKE(PokeNotifyEvent.class),
    /**
     * 红包运气王事件
     *
     * @since 2024/8/1 下午10:16 v0.0.2-dev
     */
    LUCKY_KING(LuckyKingNotifyEvent.class),
    /**
     * 群内荣耀变更事件
     *
     * @since 2024/8/1 下午10:16 v0.0.2-dev
     */
    HONOR(HonorNotifyEvent.class);
    public final Class<? extends NotifyEvent> clazz;

    NotifySubType(Class<? extends NotifyEvent> clazz) {
        this.clazz = clazz;
    }
}
