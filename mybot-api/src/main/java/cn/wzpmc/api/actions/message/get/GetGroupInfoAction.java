package cn.wzpmc.api.actions.message.get;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.entities.GroupInformation;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取群信息
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:41
 */
public class GetGroupInfoAction extends Action<GetGroupInfoAction.Params, GroupInformation> {
    /**
     * 获取群信息
     *
     * @param groupId 群号
     * @param noCache 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
     * @author wzp
     * @since 2024/8/24 23:53 v0.0.6-dev
     */
    public GetGroupInfoAction(Long groupId, Boolean noCache) {
        super.setAction(Actions.GET_GROUP_INFO);
        super.setParams(new Params(groupId, noCache));
    }

    /**
     * 获取群信息
     *
     * @param groupId 群号
     * @author wzp
     * @since 2024/8/24 23:53 v0.0.6-dev
     */
    public GetGroupInfoAction(Long groupId) {
        this(groupId, false);
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         *
         * @since 2024/8/24 22:58 v0.0.6-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
         *
         * @since 2024/8/24 22:58 v0.0.6-dev
         */
        @JSONField(name = "no_cache", defaultValue = "false")
        private Boolean noCache;
    }
}
