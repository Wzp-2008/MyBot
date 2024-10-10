package cn.wzpmc.events.notice.essence;

/**
 * 精华消息子事件
 *
 * @author wzp
 * @version 1.0.3
 * @since 2024/9/16 21:31
 */
public enum EssenceSubType {
    /**
     * 添加精华消息事件
     *
     * @since 2024/9/16 21:35 v1.0.3
     */
    ADD(EssenceAddEvent.class);
    public final Class<? extends EssenceEvent> clazz;

    EssenceSubType(Class<? extends EssenceEvent> clazz) {
        this.clazz = clazz;
    }
}
