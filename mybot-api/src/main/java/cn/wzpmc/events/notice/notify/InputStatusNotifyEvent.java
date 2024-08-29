package cn.wzpmc.events.notice.notify;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户正在输入事件
 *
 * @author wzp
 * @version 1.0.2
 * @since 2024/8/29 21:52
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InputStatusNotifyEvent extends NotifyEvent {
    /**
     * 状态文本
     *
     * @since 2024/8/29 23:16 v1.0.2
     */
    @JSONField(name = "status_text")
    private String statusText;
}
