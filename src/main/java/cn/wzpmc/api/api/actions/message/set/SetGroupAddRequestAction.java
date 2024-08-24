package cn.wzpmc.api.api.actions.message.set;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.events.request.group.GroupJoinRequestEventSubType;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 处理加群请求／邀请
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:18
 */
public class SetGroupAddRequestAction extends Action<SetGroupAddRequestAction.Params, Void> {
    /**
     * 处理加群请求／邀请
     * @author wzp
     * @since 2024/8/24 23:56 v0.0.6-dev
     * @param flag 加群请求的 flag（需从上报的数据中获得）
     * @param type 请求类型
     * @param approve 是否同意请求／邀请
     * @param reason 拒绝理由（仅在拒绝时有效）
     */
    public SetGroupAddRequestAction(String flag, GroupJoinRequestEventSubType type, boolean approve, String reason) {
        super.setAction(Actions.SET_GROUP_ADD_REQUEST);
        super.setParams(new Params(flag, type, approve, reason));
    }

    /**
     * 处理加群请求／邀请
     * @author wzp
     * @since 2024/8/24 23:56 v0.0.6-dev
     * @param flag 加群请求的 flag（需从上报的数据中获得）
     * @param type 请求类型
     * @param reason 拒绝理由（仅在拒绝时有效）
     */
    public SetGroupAddRequestAction(String flag, GroupJoinRequestEventSubType type, String reason) {
        this(flag, type, false, reason);
    }

    /**
     * 处理加群请求／邀请
     * @author wzp
     * @since 2024/8/24 23:56 v0.0.6-dev
     * @param flag 加群请求的 flag（需从上报的数据中获得）
     * @param type 请求类型
     */
    public SetGroupAddRequestAction(String flag, GroupJoinRequestEventSubType type) {
        this(flag, type, true, "");
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 加群请求的 flag（需从上报的数据中获得）
         * @since 2024/8/24 21:14 v0.0.6-dev
         */
        private String flag;
        /**
         * 请求类型
         * @since 2024/8/24 21:14 v0.0.6-dev
         */
        private GroupJoinRequestEventSubType type;
        /**
         * 是否同意请求／邀请
         * @since 2024/8/24 21:14 v0.0.6-dev
         */
        @JSONField(defaultValue = "true")
        private boolean approve;
        /**
         * 拒绝理由（仅在拒绝时有效）
         * @since 2024/8/24 21:14 v0.0.6-dev
         */
        private String reason;
    }
}
