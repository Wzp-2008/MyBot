package cn.wzpmc.api.actions.message.get;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.entities.GroupMemberInformation;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取群成员信息
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:42
 */
public class GetGroupMemberInfoAction extends Action<GetGroupMemberInfoAction.Params, GroupMemberInformation> {
    /**
     * 获取群成员信息
     *
     * @param groupId 群号
     * @param userId  QQ 号
     * @param noCache 是否不使用缓存
     * @author wzp
     * @since 2024/8/24 23:54 v0.0.6-dev
     */
    public GetGroupMemberInfoAction(Long groupId, Long userId, Boolean noCache) {
        super.setAction(Actions.GET_GROUP_MEMBER_INFO);
        super.setParams(new Params(groupId, userId, noCache));
    }

    /**
     * 获取群成员信息
     *
     * @param groupId 群号
     * @param userId  QQ 号
     * @author wzp
     * @since 2024/8/24 23:54 v0.0.6-dev
     */
    public GetGroupMemberInfoAction(Long groupId, Long userId) {
        this(groupId, userId, false);
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         *
         * @since 2024/8/24 23:02 v0.0.6-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * QQ 号
         *
         * @since 2024/8/24 23:02 v0.0.6-dev
         */
        @JSONField(name = "user_id")
        private Long userId;
        /**
         * 是否不使用缓存
         *
         * @since 2024/8/24 23:02 v0.0.6-dev
         */
        @JSONField(name = "no_cache", defaultValue = "false")
        private Boolean noCache;
    }
}
