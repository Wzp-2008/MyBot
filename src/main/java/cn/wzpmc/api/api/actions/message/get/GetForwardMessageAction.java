package cn.wzpmc.api.api.actions.message.get;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.entities.MessageInformation;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取合并转发消息
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/23 19:46
 */
public class GetForwardMessageAction extends Action<GetForwardMessageAction.Params, MessageInformation> {
    /**
     * 获取合并转发消息
     * @author wzp
     * @since 2024/8/23 21:10 v0.0.5-dev
     * @param id 合并转发ID
     */
    public GetForwardMessageAction(String id){
        super.setAction(Actions.GET_FORWARD_MSG);
        super.setParams(new GetForwardMessageAction.Params(id));
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 合并转发ID
         * @since 2024/8/23 19:47 v0.0.5-dev
         */
        private String id;
    }
}
