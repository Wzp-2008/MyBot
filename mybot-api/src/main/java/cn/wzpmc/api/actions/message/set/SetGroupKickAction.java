package cn.wzpmc.api.actions.message.set;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 群组踢人
 *
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/23 19:52
 */
public class SetGroupKickAction extends Action<SetGroupKickAction.Params, Void> {
    /**
     * 群组踢人
     *
     * @param groupId          群号
     * @param userId           要踢的 QQ 号
     * @param rejectAddRequest 拒绝此人的加群请求
     * @author wzp
     * @since 2024/8/23 21:24 v0.0.5-dev
     */
    public SetGroupKickAction(Long groupId, Long userId, Boolean rejectAddRequest) {
        super.setAction(Actions.SET_GROUP_KICK);
        super.setParams(new Params(groupId, userId, rejectAddRequest));
    }

    /**
     * 群组踢人
     *
     * @param groupId 群号
     * @param userId  要踢的 QQ 号
     * @author wzp
     * @since 2024/8/23 21:24 v0.0.5-dev
     */
    public SetGroupKickAction(Long groupId, Long userId) {
        this(groupId, userId, false);
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         *
         * @since 2024/8/23 21:25 v0.0.5-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 要踢的 QQ 号
         *
         * @since 2024/8/23 21:25 v0.0.5-dev
         */
        @JSONField(name = "user_id")
        private Long userId;
        /**
         * 拒绝此人的加群请求（默认为false）
         *
         * @since 2024/8/23 21:25 v0.0.5-dev
         */
        @JSONField(name = "reject_add_request", defaultValue = "false")
        private Boolean rejectAddRequest;
    }
}
