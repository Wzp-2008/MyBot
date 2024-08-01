package cn.wzpmc.api.events;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 事件基类
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午5:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    /**
     * 事件发生的时间戳
     * @since 2024/8/1 下午5:52 v0.0.2-dev
     */
    private Long time;
    /**
     * 收到事件的机器人的QQ号
     * @since 2024/8/1 下午5:52 v0.0.2-dev
     */
    @JSONField(name = "self_id")
    private Long selfId;
    /**
     * 事件类型
     * @since 2024/8/1 下午5:52 v0.0.2-dev
     */
    @JSONField(name = "post_type")
    private EventPostType postType;
}
