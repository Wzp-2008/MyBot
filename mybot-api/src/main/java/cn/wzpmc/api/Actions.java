package cn.wzpmc.api;

import cn.wzpmc.api.actions.message.send.SendMessageActionResponseData;
import cn.wzpmc.entities.*;
import cn.wzpmc.user.IBot;
import cn.wzpmc.user.IUser;

/**
 * 操作类型
 *
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/16 22:24
 */
public enum Actions {
    /**
     * 发送私聊消息
     *
     * @since 2024/8/23 21:07 v0.0.5-dev
     */
    SEND_PRIVATE_MSG(SendMessageActionResponseData.class),
    /**
     * 发送群消息
     *
     * @since 2024/8/23 21:07 v0.0.5-dev
     */
    SEND_GROUP_MSG(SendMessageActionResponseData.class),
    /**
     * 撤回消息
     *
     * @since 2024/8/23 21:07 v0.0.5-dev
     */
    DELETE_MSG,
    /**
     * 获取消息
     *
     * @since 2024/8/23 21:07 v0.0.5-dev
     */
    GET_MSG(MessageInformation.class),
    /**
     * 获取合并转发消息
     *
     * @since 2024/8/23 21:08 v0.0.5-dev
     */
    GET_FORWARD_MSG(MessageInformation.class),
    /**
     * 发送好友赞
     *
     * @since 2024/8/23 21:08 v0.0.5-dev
     */
    SEND_LIKE,
    /**
     * 群组踢人
     *
     * @since 2024/8/23 21:08 v0.0.5-dev
     */
    SET_GROUP_KICK,
    /**
     * 群组单人禁言
     *
     * @since 2024/8/23 21:08 v0.0.5-dev
     */
    SET_GROUP_BAN,
    /**
     * 群组匿名用户禁言
     *
     * @since 2024/8/23 21:08 v0.0.5-dev
     */
    SET_GROUP_ANONYMOUS_BAN,
    /**
     * 群组全员禁言
     *
     * @since 2024/8/23 21:09 v0.0.5-dev
     */
    SET_GROUP_WHOLE_BAN,
    /**
     * 群组设置管理员
     *
     * @since 2024/8/23 21:09 v0.0.5-dev
     */
    SET_GROUP_ADMIN,
    /**
     * 群组匿名
     *
     * @since 2024/8/23 21:09 v0.0.5-dev
     */
    SET_GROUP_ANONYMOUS,
    /**
     * 设置群名片（群备注）
     *
     * @since 2024/8/23 21:09 v0.0.5-dev
     */
    SET_GROUP_CARD,
    /**
     * 设置群名
     *
     * @since 2024/8/24 19:04 v0.0.6-dev
     */
    SET_GROUP_NAME,
    /**
     * 退出群组
     *
     * @since 2024/8/24 20:01 v0.0.6-dev
     */
    SET_GROUP_LEAVE,
    /**
     * 设置群组专属头衔
     *
     * @since 2024/8/24 20:01 v0.0.6-dev
     */
    SET_GROUP_SPECIAL_TITLE,
    /**
     * 处理加好友请求
     *
     * @since 2024/8/24 20:01 v0.0.6-dev
     */
    SET_FRIEND_ADD_REQUEST,
    /**
     * 处理加群请求／邀请
     *
     * @since 2024/8/24 20:02 v0.0.6-dev
     */
    SET_GROUP_ADD_REQUEST,
    /**
     * 获取登录号信息
     *
     * @since 2024/8/24 20:02 v0.0.6-dev
     */
    GET_LOGIN_INFO(IBot.class),
    /**
     * 获取陌生人信息
     *
     * @since 2024/8/24 20:02 v0.0.6-dev
     */
    GET_STRANGER_INFO(IUser.class),
    /**
     * 获取好友列表
     *
     * @since 2024/8/24 20:02 v0.0.6-dev
     */
    GET_FRIEND_LIST(MessageInformation.class, true),
    /**
     * 获取群信息
     *
     * @since 2024/8/24 20:02 v0.0.6-dev
     */
    GET_GROUP_INFO(GroupInformation.class),
    /**
     * 获取群列表
     *
     * @since 2024/8/24 20:02 v0.0.6-dev
     */
    GET_GROUP_LIST(GroupInformation.class, true),
    /**
     * 获取群成员信息
     *
     * @since 2024/8/24 20:02 v0.0.6-dev
     */
    GET_GROUP_MEMBER_INFO(GroupMemberInformation.class),
    /**
     * 获取群成员列表
     *
     * @since 2024/8/24 20:03 v0.0.6-dev
     */
    GET_GROUP_MEMBER_LIST(GroupMemberInformation.class, true),
    /**
     * 获取群荣誉信息
     *
     * @since 2024/8/24 20:03 v0.0.6-dev
     */
    GET_GROUP_HONOR_INFO(HonorInformation.class),
    /**
     * 获取 Cookies
     *
     * @since 2024/8/24 20:03 v0.0.6-dev
     */
    GET_COOKIES(CookiesInformation.class),
    /**
     * 获取 CSRF Token
     *
     * @since 2024/8/24 20:04 v0.0.6-dev
     */
    GET_CSRF_TOKEN(CsrfTokenInformation.class),
    /**
     * 获取 QQ 相关接口凭证
     *
     * @since 2024/8/24 20:04 v0.0.6-dev
     */
    GET_CREDENTIALS(CredentialsInformation.class),
    /**
     * 获取语音
     *
     * @since 2024/8/24 20:04 v0.0.6-dev
     */
    GET_RECORD(FilePathInformation.class),
    /**
     * 获取图片
     *
     * @since 2024/8/24 20:04 v0.0.6-dev
     */
    GET_IMAGE(FilePathInformation.class),
    /**
     * 检查是否可以发送图片
     *
     * @since 2024/8/24 20:04 v0.0.6-dev
     */
    CAN_SEND_IMAGE(YesNo.class),
    /**
     * 检查是否可以发送语音
     *
     * @since 2024/8/24 20:04 v0.0.6-dev
     */
    CAN_SEND_RECORD(YesNo.class),
    /**
     * 获取运行状态
     *
     * @since 2024/8/24 20:04 v0.0.6-dev
     */
    GET_STATUS(BotStatus.class),
    /**
     * 获取版本信息
     *
     * @since 2024/8/24 20:04 v0.0.6-dev
     */
    GET_VERSION_INFO(VersionInformation.class),
    /**
     * 重启 OneBot 实现
     *
     * @since 2024/8/24 20:05 v0.0.6-dev
     */
    SET_RESTART,
    /**
     * 清理缓存
     *
     * @since 2024/8/24 20:05 v0.0.6-dev
     */
    CLEAN_CACHE;
    public final Class<?> responseClass;
    public final boolean array;

    Actions(Class<?> responseClass, boolean array) {
        this.responseClass = responseClass;
        this.array = array;
    }

    Actions(Class<?> responseClass) {
        this(responseClass, false);
    }

    Actions() {
        this(Void.class);
    }
}
