package cn.wzpmc.api.actions.message.send;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.message.MessageComponent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 发送私聊消息
 *
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/17 21:54
 */
public class SendPrivateMessageAction extends Action<SendPrivateMessageAction.Params, SendMessageActionResponseData> {
    /**
     * 发送私聊消息
     *
     * @param userId     对方 QQ 号
     * @param message    要发送的内容
     * @param autoEscape 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     * @author wzp
     * @since 2024/8/23 21:13 v0.0.5-dev
     */
    public SendPrivateMessageAction(Long userId, MessageComponent message, boolean autoEscape) {
        super.setAction(Actions.SEND_PRIVATE_MSG);
        super.setParams(new Params(userId, message, autoEscape));
    }

    /**
     * 发送私聊消息
     *
     * @param userId  对方 QQ 号
     * @param message 要发送的内容
     * @author wzp
     * @since 2024/8/23 21:14 v0.0.5-dev
     */
    public SendPrivateMessageAction(Long userId, MessageComponent message) {
        this(userId, message, false);
    }

    @Data
    @AllArgsConstructor
    public static class Params {
        /**
         * 对方 QQ 号
         *
         * @since 2024/8/17 22:51 v0.0.5-dev
         */
        @JSONField(name = "user_id")
        private Long userId;
        /**
         * 要发送的内容
         *
         * @since 2024/8/17 22:51 v0.0.5-dev
         */
        private MessageComponent message;
        /**
         * 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
         *
         * @since 2024/8/17 22:51 v0.0.5-dev
         */
        @JSONField(name = "auto_escape")
        private boolean autoEscape;
    }
}
