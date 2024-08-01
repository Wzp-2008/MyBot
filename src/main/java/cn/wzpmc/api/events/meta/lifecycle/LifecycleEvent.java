package cn.wzpmc.api.events.meta.lifecycle;

import cn.wzpmc.api.events.meta.MetaEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 生命周期事件
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LifecycleEvent extends MetaEvent {
    /**
     * 生命周期事件子类型
     * @since 2024/8/1 下午10:29 v0.0.2-dev
     */
    @JSONField(name = "sub_type")
    private LifecycleEventSubType subType;
}
