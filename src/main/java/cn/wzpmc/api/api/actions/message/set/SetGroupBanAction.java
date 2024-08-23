package cn.wzpmc.api.api.actions.message.set;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 群组单人禁言
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/23 20:03
 */
public class SetGroupBanAction extends Action<SetGroupBanAction.Params, Void> {
    /**
     * 群组单人禁言
     * @author wzp
     * @since 2024/8/23 21:20 v0.0.5-dev
     * @param groupId 群号
     * @param userId 要禁言的 QQ 号
     * @param duration 禁言时长，单位秒，0 表示取消禁言
     */
    public SetGroupBanAction(Long groupId, Long userId, Long duration){
        super.setAction(Actions.SET_GROUP_BAN);
        super.setParams(new Params(groupId, userId, duration));
    }

    /**
     * 群组单人禁言
     * @author wzp
     * @since 2024/8/23 21:20 v0.0.5-dev
     * @param groupId 群号
     * @param userId 要禁言的 QQ 号
     */
    public SetGroupBanAction(Long groupId, Long userId){
        this(groupId, userId, 1800L);
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         * @since 2024/8/23 21:20 v0.0.5-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 要禁言的 QQ 号
         * @since 2024/8/23 21:20 v0.0.5-dev
         */
        @JSONField(name = "user_id")
        private Long userId;
        /**
         * 禁言时长，单位秒，0 表示取消禁言（默认30 * 60）
         * @since 2024/8/23 21:20 v0.0.5-dev
         */
        @JSONField(defaultValue = "1800")
        private Long duration;
    }
}
