package cn.wzpmc.api.actions.message.set;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 退出群组
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:07
 */
public class SetGroupLeaveAction extends Action<SetGroupLeaveAction.Params, Void> {
    /**
     * 退出群组
     *
     * @param groupId   群号
     * @param isDismiss 是否解散，如果登录号是群主，则仅在此项为 true 时能够解散
     * @author wzp
     * @since 2024/8/24 23:57 v0.0.6-dev
     */
    public SetGroupLeaveAction(Long groupId, boolean isDismiss) {
        super.setAction(Actions.SET_GROUP_LEAVE);
        super.setParams(new Params(groupId, isDismiss));
    }

    /**
     * 退出群组
     *
     * @param groupId 群号
     * @author wzp
     * @since 2024/8/24 23:57 v0.0.6-dev
     */
    public SetGroupLeaveAction(Long groupId) {
        this(groupId, false);
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         *
         * @since 2024/8/24 21:02 v0.0.6-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 是否解散，如果登录号是群主，则仅在此项为 true 时能够解散
         *
         * @since 2024/8/24 21:02 v0.0.6-dev
         */
        @JSONField(name = "is_dismiss", defaultValue = "false")
        private boolean isDismiss;
    }
}
