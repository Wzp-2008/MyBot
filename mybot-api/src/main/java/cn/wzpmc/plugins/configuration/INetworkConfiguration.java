package cn.wzpmc.plugins.configuration;

/**
 * @author wzp
 * @since 2025/3/26 17:26
 * @version 1.0.7
 */
public interface INetworkConfiguration {
    /**
     * @return WebSocket连接URL
     * @author wzp
     * @since 2025/3/26 17:26 v1.0.7
     */
    String getWebsocket();

    /**
     * @author wzp
     * @since 2025/3/26 17:27 v1.0.7
     * @return 是否启用连接重试
     */
    Boolean isRetry();

    /**
     * @author wzp
     * @since 2025/3/26 17:27 v1.0.7
     * @return 重试最大次数（-1为无限）
     */
    Integer getMaxRetryCount();


    /**
     * @author wzp
     * @since 2025/3/26 17:28 v1.0.7
     * @return 获取重试间隔（单位毫秒）
     */
    Long getRetryInterval();

}
