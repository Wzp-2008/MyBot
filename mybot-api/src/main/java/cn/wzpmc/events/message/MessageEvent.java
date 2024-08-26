package cn.wzpmc.events.message;

import cn.wzpmc.events.Event;
import cn.wzpmc.message.StringMessage;
import cn.wzpmc.message.json.JsonMessage;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 消息事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午5:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageEvent<E, U> extends Event {
    /**
     * 消息子类型
     *
     * @since 2024/8/1 下午11:11 v0.0.2-dev
     */
    @JSONField(name = "sub_type")
    private E subType;
    /**
     * 消息类型
     *
     * @since 2024/8/1 下午11:24 v0.0.2-dev
     */
    @JSONField(name = "message_type")
    private MessageType messageType;
    /**
     * 消息ID
     *
     * @since 2024/8/1 下午11:11 v0.0.2-dev
     */
    @JSONField(name = "message_id")
    private Integer messageId;
    /**
     * 发送者ID
     *
     * @since 2024/8/1 下午11:11 v0.0.2-dev
     */
    @JSONField(name = "user_id")
    private Integer userId;
    /**
     * 消息详细内容
     *
     * @since 2024/8/1 下午11:11 v0.0.2-dev
     */
    private JsonMessage message;
    /**
     * 文本格式消息
     *
     * @since 2024/8/1 下午11:11 v0.0.2-dev
     */
    @JSONField(name = "raw_message")
    private StringMessage rawMessage;
    /**
     * 消息使用字体
     *
     * @since 2024/8/1 下午11:11 v0.0.2-dev
     */
    private Integer font;
    /**
     * 发送者详细信息
     *
     * @since 2024/8/1 下午11:12 v0.0.2-dev
     */
    private U sender;
}
