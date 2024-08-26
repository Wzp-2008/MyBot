package cn.wzpmc.api.actions.message.set;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 设置群名
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:07
 */
public class SetGroupNameAction extends Action<SetGroupNameAction.Params, Void> {
    /**
     * 设置群名
     *
     * @param groupId   群号
     * @param groupName 新群名
     * @author wzp
     * @since 2024/8/24 23:57 v0.0.6-dev
     */
    public SetGroupNameAction(Long groupId, String groupName) {
        super.setAction(Actions.SET_GROUP_NAME);
        super.setParams(new Params(groupId, groupName));
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         *
         * @since 2024/8/24 20:59 v0.0.6-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 新群名
         *
         * @since 2024/8/24 21:00 v0.0.6-dev
         */
        @JSONField(name = "group_name")
        private String groupName;
    }
}
