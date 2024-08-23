package cn.wzpmc.api.api;

import cn.wzpmc.api.api.actions.message.send.SendMessageActionResponseData;
import cn.wzpmc.api.entities.MessageInformation;

/**
 * 操作类型
 * @author wzp
 * @since 2024/8/16 22:24
 * @version 0.0.5-dev
 */
public enum Actions {
    /**
     * 发送私聊消息
     * @since 2024/8/23 21:07 v0.0.5-dev
     */
    SEND_PRIVATE_MSG(SendMessageActionResponseData.class),
    /**
     * 发送群消息
     * @since 2024/8/23 21:07 v0.0.5-dev
     */
    SEND_GROUP_MSG(SendMessageActionResponseData.class),
    /**
     * 撤回消息
     * @since 2024/8/23 21:07 v0.0.5-dev
     */
    DELETE_MSG(),
    /**
     * 获取消息
     * @since 2024/8/23 21:07 v0.0.5-dev
     */
    GET_MSG(MessageInformation.class),
    /**
     * 获取合并转发消息
     * @since 2024/8/23 21:08 v0.0.5-dev
     */
    GET_FORWARD_MSG(MessageInformation.class),
    /**
     * 发送好友赞
     * @since 2024/8/23 21:08 v0.0.5-dev
     */
    SEND_LIKE(),
    /**
     * 群组踢人
     * @since 2024/8/23 21:08 v0.0.5-dev
     */
    SET_GROUP_KICK(),
    /**
     * 群组单人禁言
     * @since 2024/8/23 21:08 v0.0.5-dev
     */
    SET_GROUP_BAN(),
    /**
     * 群组匿名用户禁言
     * @since 2024/8/23 21:08 v0.0.5-dev
     */
    SET_GROUP_ANONYMOUS_BAN(),
    /**
     * 群组全员禁言
     * @since 2024/8/23 21:09 v0.0.5-dev
     */
    SET_GROUP_WHOLE_BAN(),
    /**
     * 群组设置管理员
     * @since 2024/8/23 21:09 v0.0.5-dev
     */
    SET_GROUP_ADMIN(),
    /**
     * 群组匿名
     * @since 2024/8/23 21:09 v0.0.5-dev
     */
    SET_GROUP_ANONYMOUS(),
    /**
     * 设置群名片（群备注）
     * @since 2024/8/23 21:09 v0.0.5-dev
     */
    SET_GROUP_CARD();
    public final Class<?> responseClass;
    Actions(Class<?> responseClass) {
        this.responseClass = responseClass;
    }
    Actions(){
        this(Void.class);
    }
}
