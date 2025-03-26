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
     * @author wzp
     * @since 2025/3/26 17:29 v1.0.7
     * @return 网络相关配置
     */
    INetworkConfiguration getNetwork();
    /**
     *
     * @author wzp
     * @since 2025/2/4 13:44 v1.0.5
     * @return 命令前缀
     */
    String getCommandPrefix();

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
