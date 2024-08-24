package cn.wzpmc.api.api.actions.message.set;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 群组匿名
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/23 20:55
 */
public class SetGroupAnonymousAction extends Action<SetGroupAnonymousAction.Params, Void> {
    /**
     * 群组匿名
     * @author wzp
     * @since 2024/8/23 21:16 v0.0.5-dev
     * @param groupId 群号
     * @param enable 是否允许匿名聊天
     */
    public SetGroupAnonymousAction(Long groupId, Boolean enable) {
        super.setAction(Actions.SET_GROUP_ANONYMOUS);
        super.setParams(new Params(groupId, enable));
    }

    /**
     * 群组匿名
     * @author wzp
     * @since 2024/8/23 21:16 v0.0.5-dev
     * @param groupId 群号
     */
    public SetGroupAnonymousAction(Long groupId) {
        this(groupId, true);
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         * @since 2024/8/23 21:16 v0.0.5-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 是否允许匿名聊天
         * @since 2024/8/23 21:16 v0.0.5-dev
         */
        @JSONField(defaultValue = "true")
        private Boolean enable;
    }
}
