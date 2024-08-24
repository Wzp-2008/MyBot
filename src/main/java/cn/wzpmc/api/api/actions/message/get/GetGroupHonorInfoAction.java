package cn.wzpmc.api.api.actions.message.get;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.entities.HonorInformation;
import cn.wzpmc.api.entities.HonorType;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取群荣誉信息
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:43
 */
public class GetGroupHonorInfoAction extends Action<GetGroupHonorInfoAction.Params, HonorInformation> {
    /**
     * 获取群荣誉信息
     * @author wzp
     * @since 2024/8/24 23:52 v0.0.6-dev
     * @param groupId 群号
     * @param type 要获取的群荣誉类型，可传入 talkative performer legend strong_newbie emotion 以分别获取单个类型的群荣誉数据，或传入 all 获取所有数据
     */
    public GetGroupHonorInfoAction(Long groupId, HonorType type) {
        this.setAction(Actions.GET_GROUP_HONOR_INFO);
        this.setParams(new Params(groupId, type));
    }

    /**
     * 获取群荣誉信息
     * @author wzp
     * @since 2024/8/24 23:53 v0.0.6-dev
     * @param groupId 群号
     */
    public GetGroupHonorInfoAction(Long groupId) {
        this(groupId, HonorType.ALL);
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         * @since 2024/8/24 23:52 v0.0.6-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 要获取的群荣誉类型，可传入 talkative performer legend strong_newbie emotion 以分别获取单个类型的群荣誉数据，或传入 all 获取所有数据
         * @since 2024/8/24 23:52 v0.0.6-dev
         */
        private HonorType type;
    }
}
