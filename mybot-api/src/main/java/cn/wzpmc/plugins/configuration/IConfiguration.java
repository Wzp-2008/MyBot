package cn.wzpmc.plugins.configuration;

/**
 * 配置
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午3:42
 */
public interface IConfiguration {
    /**
     * @return WebSocket连接URL
     * @author wzp
     * @since 2024/7/31 上午3:48 v0.0.1-dev
     */
    String getWebsocket();

    /**
     * @return 通信验证
     * @author wzp
     * @since 2024/7/31 上午3:48 v0.0.1-dev
     */
    IAuthorizationConfiguration getAuthorization();

    /**
     * @return 失败消息提示
     * @author wzp
     * @since 2024/7/31 上午3:49 v0.0.1-dev
     */
    IFallbackConfiguration getFallback();

    /**
     * @return 获取好友配置
     * @author wzp
     * @since 2024/8/30 15:42 v1.0.3
     */
    IFriendConfiguration getFriend();

    /**
     * @return 获取群聊配置
     * @author wzp
     * @since 2024/8/31 23:56 v1.0.3
     */
    IGroupConfiguration getGroup();
}
