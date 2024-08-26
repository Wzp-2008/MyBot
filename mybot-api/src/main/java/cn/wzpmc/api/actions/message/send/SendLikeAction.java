package cn.wzpmc.api.actions.message.send;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 发送好友赞
 *
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/23 19:49
 */
public class SendLikeAction extends Action<SendLikeAction.Params, Void> {
    /**
     * 发送好友赞
     *
     * @param userId 对方 QQ 号
     * @param times  赞的次数，每个好友每天最多 10 次
     * @author wzp
     * @since 2024/8/23 21:12 v0.0.5-dev
     */
    public SendLikeAction(Long userId, Long times) {
        super.setAction(Actions.SEND_LIKE);
        super.setParams(new Params(userId, times));
    }

    /**
     * 发送好友赞
     *
     * @param userId 对方 QQ 号
     * @author wzp
     * @since 2024/8/23 21:12 v0.0.5-dev
     */
    public SendLikeAction(Long userId) {
        this(userId, 1L);
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 对方 QQ 号
         *
         * @since 2024/8/23 21:12 v0.0.5-dev
         */
        @JSONField(name = "user_id")
        private Long userId;
        /**
         * 赞的次数，每个好友每天最多 10 次
         *
         * @since 2024/8/23 21:12 v0.0.5-dev
         */
        @JSONField(defaultValue = "1")
        private Long times;
    }
}
