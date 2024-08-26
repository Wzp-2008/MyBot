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
}
