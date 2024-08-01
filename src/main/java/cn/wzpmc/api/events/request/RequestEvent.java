package cn.wzpmc.api.events.request;

import cn.wzpmc.api.events.Event;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求基事件
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RequestEvent extends Event {
    /**
     * 请求类型
     * @since 2024/8/1 下午10:10 v0.0.2-dev
     */
    @JSONField(name = "request_type")
    private RequestEventType requestType;
    /**
     * 发送请求的用户ID
     * @since 2024/8/1 下午10:10 v0.0.2-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
    /**
     * 验证消息
     * @since 2024/8/1 下午10:10 v0.0.2-dev
     */
    private String comment;
    /**
     * 请求Flag
     * @since 2024/8/1 下午10:10 v0.0.2-dev
     */
    private String flag;
}
