package cn.wzpmc.events.meta;

import cn.wzpmc.events.meta.lifecycle.LifecycleEvent;

/**
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:26
 */
public enum MetaEventType {
    LIFECYCLE(LifecycleEvent.class),
    HEARTBEAT(HeartBeatEvent.class);
    public final Class<? extends MetaEvent> clazz;

    MetaEventType(Class<? extends MetaEvent> clazz) {
        this.clazz = clazz;
    }
}
