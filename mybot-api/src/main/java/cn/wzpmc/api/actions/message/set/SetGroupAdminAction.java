package cn.wzpmc.api.actions.message.set;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 群组设置管理员
 *
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/23 20:51
 */
public class SetGroupAdminAction extends Action<SetGroupAdminAction.Params, Void> {
    /**
     * 群组设置管理员
     *
     * @param groupId 群号
     * @param userId  要设置管理员的 QQ 号
     * @param enable  true 为设置，false 为取消
     * @author wzp
     * @since 2024/8/23 21:14 v0.0.5-dev
     */
    public SetGroupAdminAction(Long groupId, Long userId, Boolean enable) {
        super.setAction(Actions.SET_GROUP_ADMIN);
        super.setParams(new Params(groupId, userId, enable));
    }

    /**
     * 群组设置管理员
     *
     * @param groupId 群号
     * @param userId  要设置管理员的 QQ 号
     * @author wzp
     * @since 2024/8/23 21:14 v0.0.5-dev
     */
    public SetGroupAdminAction(Long groupId, Long userId) {
        this(groupId, userId, true);
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         *
         * @since 2024/8/23 21:15 v0.0.5-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 要设置管理员的 QQ 号
         *
         * @since 2024/8/23 21:15 v0.0.5-dev
         */
        @JSONField(name = "user_id")
        private Long userId;
        /**
         * true 为设置，false 为取消
         *
         * @since 2024/8/23 21:15 v0.0.5-dev
         */
        @JSONField(defaultValue = "true")
        private Boolean enable;
    }
}
