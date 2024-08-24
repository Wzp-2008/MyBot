package cn.wzpmc.api.api.actions.message.set;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 处理加好友请求
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:08
 */
public class SetFriendAddRequestAction extends Action<SetFriendAddRequestAction.Params, Void> {
    /**
     * 处理加好友请求
     * @author wzp
     * @since 2024/8/24 23:56 v0.0.6-dev
     * @param flag 加好友请求的 flag（需从上报的数据中获得）
     * @param approve 是否同意请求
     * @param remark 添加后的好友备注（仅在同意时有效）
     */
    public SetFriendAddRequestAction(String flag, boolean approve, String remark){
        super.setAction(Actions.SET_FRIEND_ADD_REQUEST);
        super.setParams(new Params(flag, approve, remark));
    }

    /**
     * 处理加好友请求
     * @author wzp
     * @since 2024/8/24 23:56 v0.0.6-dev
     * @param flag 加好友请求的 flag（需从上报的数据中获得）
     * @param approve 是否同意请求
     */
    public SetFriendAddRequestAction(String flag, boolean approve) {
        this(flag, approve, "");
    }

    /**
     * 处理加好友请求
     * @author wzp
     * @since 2024/8/24 23:56 v0.0.6-dev
     * @param flag 加好友请求的 flag（需从上报的数据中获得）
     * @param remark 添加后的好友备注（仅在同意时有效）
     */
    public SetFriendAddRequestAction(String flag, String remark) {
        this(flag, true, remark);
    }

    /**
     * 处理加好友请求
     * @author wzp
     * @since 2024/8/24 23:56 v0.0.6-dev
     * @param flag 加好友请求的 flag（需从上报的数据中获得）
     */
    public SetFriendAddRequestAction(String flag) {
        this(flag, true);
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 加好友请求的 flag（需从上报的数据中获得）
         * @since 2024/8/24 21:09 v0.0.6-dev
         */
        private String flag;
        /**
         * 是否同意请求
         * @since 2024/8/24 21:09 v0.0.6-dev
         */
        @JSONField(defaultValue = "true")
        private boolean approve;
        /**
         * 添加后的好友备注（仅在同意时有效）
         * @since 2024/8/24 21:09 v0.0.6-dev
         */
        private String remark;
    }
}
