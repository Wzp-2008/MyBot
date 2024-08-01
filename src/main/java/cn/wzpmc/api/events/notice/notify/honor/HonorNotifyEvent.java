package cn.wzpmc.api.events.notice.notify.honor;

import cn.wzpmc.api.events.notice.notify.NotifyEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群荣耀变更事件
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HonorNotifyEvent extends NotifyEvent {
    /**
     * 荣耀类型
     * @since 2024/8/1 下午10:06 v0.0.2-dev
     */
    @JSONField(name = "honor_type")
    private HonorType honorType;

}
