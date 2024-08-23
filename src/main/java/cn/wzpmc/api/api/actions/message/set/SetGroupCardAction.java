package cn.wzpmc.api.api.actions.message.set;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 设置群名片（群备注）
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/23 20:59
 */
public class SetGroupCardAction extends Action<SetGroupCardAction.Params, Void> {
    /**
     * 设置群名片（群备注）
     * @author wzp
     * @since 2024/8/23 21:22 v0.0.5-dev
     * @param groupId 群号
     * @param userId 要设置的 QQ 号
     * @param card 群名片内容，不填或空字符串表示删除群名片
     */
    public SetGroupCardAction(Long groupId, Long userId, String card) {
        super.setAction(Actions.SET_GROUP_CARD);
        super.setParams(new Params(groupId, userId, card));
    }

    /**
     * 设置群名片（群备注）
     * @author wzp
     * @since 2024/8/23 21:22 v0.0.5-dev
     * @param groupId 群号
     * @param userId 要设置的 QQ 号
     */
    public SetGroupCardAction(Long groupId, Long userId) {
        this(groupId, userId, "");
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         * @since 2024/8/23 21:23 v0.0.5-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 要设置的 QQ 号
         * @since 2024/8/23 21:23 v0.0.5-dev
         */
        @JSONField(name = "user_id")
        private Long userId;
        /**
         * 群名片内容，不填或空字符串表示删除群名片（默认为空）
         * @since 2024/8/23 21:23 v0.0.5-dev
         */
        private String card;
    }
}
