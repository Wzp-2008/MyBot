package cn.wzpmc.api.actions.message.set;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.message.json.parts.Anonymous;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 群组匿名用户禁言
 *
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/23 20:08
 */
public class SetGroupAnonymousBanAction extends Action<SetGroupAnonymousBanAction.Params, Void> {
    /**
     * 群组匿名用户禁言
     *
     * @param groupId   群号
     * @param anonymous 可选，要禁言的匿名用户对象（群消息上报的 anonymous 字段）
     * @param flag      可选，要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
     * @param duration  禁言时长，单位秒，无法取消匿名用户禁言（默认30 * 60）
     * @author wzp
     * @since 2024/8/23 21:17 v0.0.5-dev
     */
    protected SetGroupAnonymousBanAction(Long groupId, Anonymous anonymous, String flag, Long duration) {
        super.setAction(Actions.SET_GROUP_ANONYMOUS_BAN);
        super.setParams(new Params(groupId, anonymous, flag, duration));
    }

    /**
     * 群组匿名用户禁言
     *
     * @param groupId   群号
     * @param anonymous 要禁言的匿名用户对象（群消息上报的 anonymous 字段）
     * @param duration  禁言时长，单位秒，无法取消匿名用户禁言
     * @author wzp
     * @since 2024/8/23 21:17 v0.0.5-dev
     */
    public SetGroupAnonymousBanAction(Long groupId, Anonymous anonymous, Long duration) {
        this(groupId, anonymous, null, duration);
    }

    /**
     * 群组匿名用户禁言
     *
     * @param groupId   群号
     * @param anonymous 要禁言的匿名用户对象（群消息上报的 anonymous 字段）
     * @author wzp
     * @since 2024/8/23 21:17 v0.0.5-dev
     */
    public SetGroupAnonymousBanAction(Long groupId, Anonymous anonymous) {
        this(groupId, anonymous, 1800L);
    }

    /**
     * 群组匿名用户禁言
     *
     * @param groupId  群号
     * @param flag     要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
     * @param duration 禁言时长，单位秒，无法取消匿名用户禁言
     * @author wzp
     * @since 2024/8/23 21:17 v0.0.5-dev
     */
    public SetGroupAnonymousBanAction(Long groupId, String flag, Long duration) {
        this(groupId, null, flag, duration);
    }

    /**
     * 群组匿名用户禁言
     *
     * @param groupId 群号
     * @param flag    要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
     * @author wzp
     * @since 2024/8/23 21:17 v0.0.5-dev
     */
    public SetGroupAnonymousBanAction(Long groupId, String flag) {
        this(groupId, flag, 1800L);
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         *
         * @since 2024/8/23 21:17 v0.0.5-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 可选，要禁言的匿名用户对象（群消息上报的 anonymous 字段）
         *
         * @since 2024/8/23 21:17 v0.0.5-dev
         */
        private Anonymous anonymous;
        /**
         * 可选，要禁言的匿名用户的 flag（需从群消息上报的数据中获得）
         *
         * @since 2024/8/23 21:17 v0.0.5-dev
         */
        private String flag;
        /**
         * 禁言时长，单位秒，无法取消匿名用户禁言（默认30 * 60）
         *
         * @since 2024/8/23 21:17 v0.0.5-dev
         */
        @JSONField(defaultValue = "1800")
        private Long duration;
    }
}
