package cn.wzpmc.api.api.actions.message.get;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.entities.GroupMemberInformation;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 获取群成员列表
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:42
 */
public class GetGroupMemberListAction extends Action<GetGroupMemberListAction.Params, List<GroupMemberInformation>> {
    /**
     * 获取群成员列表
     * @author wzp
     * @since 2024/8/24 23:54 v0.0.6-dev
     * @param groupId 群号
     */
    public GetGroupMemberListAction(Long groupId) {
        super.setAction(Actions.GET_GROUP_MEMBER_LIST);
        super.setParams(new Params(groupId));
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         * @since 2024/8/24 23:08 v0.0.6-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
    }
}
