package cn.wzpmc.api.api.actions.message.delete;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 撤回消息
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/17 23:02
 */
public class DeleteMessageAction extends Action<DeleteMessageAction.Params, Void> {
    /**
     * 撤回消息
     * @author wzp
     * @since 2024/8/23 21:09 v0.0.5-dev
     * @param messageId 消息ID
     */
    public DeleteMessageAction(Integer messageId){
        super.setAction(Actions.DELETE_MSG);
        super.setParams(new Params(messageId));
    }
    @Data
    @AllArgsConstructor
    public static class Params {
        /**
         * 消息ID
         * @since 2024/8/17 23:03 v0.0.5-dev
         */
        @JSONField(name = "message_id")
        private Integer messageId;
    }
}
