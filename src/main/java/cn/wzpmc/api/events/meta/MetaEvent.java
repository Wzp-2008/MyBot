package cn.wzpmc.api.events.meta;

import cn.wzpmc.api.events.Event;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 元事件
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:26
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MetaEvent extends Event {
    /**
     * 元事件子类型
     * @since 2024/8/1 下午11:12 v0.0.2-dev
     */
    @JSONField(name = "meta_event_type")
    private MetaEventType metaEventType;
}
