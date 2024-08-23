package cn.wzpmc.api.api.actions.message.get;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.entities.MessageInformation;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取消息
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/17 23:07
 */
public class GetMessageAction extends Action<GetMessageAction.Params, MessageInformation> {
    /**
     * 获取消息
     * @author wzp
     * @since 2024/8/23 21:11 v0.0.5-dev
     * @param messageId 消息 ID
     */
    public GetMessageAction(Integer messageId){
        super.setAction(Actions.GET_MSG);
        super.setParams(new Params(messageId));
    }
    @Data
    @AllArgsConstructor
    public static class Params {
        /**
         * 消息 ID
         * @since 2024/8/23 21:11 v0.0.5-dev
         */
        @JSONField(name = "message_id")
        private Integer messageId;
    }
}
