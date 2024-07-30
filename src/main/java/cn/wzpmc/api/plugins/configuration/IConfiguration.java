package cn.wzpmc.api.plugins.configuration;

/**
 * 配置
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午3:42
 */
public interface IConfiguration {
    /**
     * @author wzp
     * @since 2024/7/31 上午3:48 v0.0.1-dev
     * @return WebSocket连接URL
     */
    String getWebsocket();

    /**
     * @author wzp
     * @since 2024/7/31 上午3:48 v0.0.1-dev
     * @return 通信验证
     */
    IAuthorizationConfiguration getAuthorization();

    /**
     * @author wzp
     * @since 2024/7/31 上午3:49 v0.0.1-dev
     * @return 失败消息提示
     */
    IFallbackConfiguration getFallback();
}
