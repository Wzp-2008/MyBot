package cn.wzpmc.api.api.actions.message.send;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.message.MessageComponent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 发送群消息
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/17 22:52
 */
public class SendGroupMessageAction extends Action<SendGroupMessageAction.Params, SendMessageActionResponseData> {
    /**
     * 发送群消息
     * @author wzp
     * @since 2024/8/23 21:11 v0.0.5-dev
     * @param groupId 群号
     * @param message 要发送的内容
     * @param autoEscape 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
     */
    public SendGroupMessageAction(Long groupId, MessageComponent message, boolean autoEscape){
        super.setAction(Actions.SEND_GROUP_MSG);
        super.setParams(new SendGroupMessageAction.Params(groupId, message, autoEscape));
    }

    /**
     * 发送群消息
     * @author wzp
     * @since 2024/8/23 21:11 v0.0.5-dev
     * @param groupId 群号
     * @param message 要发送的内容
     */
    public SendGroupMessageAction(Long groupId, MessageComponent message){
        this(groupId, message, false);
    }
    @Data
    @AllArgsConstructor
    public static class Params {
        /**
         * 群号
         * @since 2024/8/17 22:54 v0.0.5-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 要发送的内容
         * @since 2024/8/17 22:54 v0.0.5-dev
         */
        private MessageComponent message;
        /**
         * 消息内容是否作为纯文本发送（即不解析 CQ 码），只在 message 字段是字符串时有效
         * @since 2024/8/17 22:51 v0.0.5-dev
         */
        @JSONField(name = "auto_escape")
        private boolean autoEscape;
    }
}
