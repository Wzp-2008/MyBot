package cn.wzpmc.api.actions.message.get;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.user.IUser;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取陌生人信息
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:41
 */
public class GetStrangerInfoAction extends Action<GetStrangerInfoAction.Params, IUser> {
    /**
     * 获取陌生人信息
     *
     * @param userId  QQ 号
     * @param noCache 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
     * @author wzp
     * @since 2024/8/24 23:55 v0.0.6-dev
     */
    public GetStrangerInfoAction(Long userId, boolean noCache) {
        super.setAction(Actions.GET_STRANGER_INFO);
        super.setParams(new GetStrangerInfoAction.Params(userId, noCache));
    }

    /**
     * 获取陌生人信息
     *
     * @param userId QQ 号
     * @author wzp
     * @since 2024/8/24 23:55 v0.0.6-dev
     */
    public GetStrangerInfoAction(Long userId) {
        this(userId, false);
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * QQ 号
         *
         * @since 2024/8/24 22:52 v0.0.6-dev
         */
        @JSONField(name = "user_id")
        private Long userId;
        /**
         * 是否不使用缓存（使用缓存可能更新不及时，但响应更快）
         *
         * @since 2024/8/24 22:52 v0.0.6-dev
         */
        @JSONField(name = "no_cache", defaultValue = "false")
        private boolean noCache;
    }
}
