package cn.wzpmc.events.notice.user.decrease;

/**
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:36
 */
public enum GroupUserDecreaseSubType {
    /**
     * 主动退群
     *
     * @since 2024/8/1 下午9:37 v0.0.2-dev
     */
    LEAVE,
    /**
     * 成员被踢出
     *
     * @since 2024/8/1 下午9:37 v0.0.2-dev
     */
    KICK,
    /**
     * 机器人被踢
     *
     * @since 2024/8/1 下午9:37 v0.0.2-dev
     */
    KICK_ME
}
