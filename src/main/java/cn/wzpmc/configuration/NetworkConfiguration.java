package cn.wzpmc.configuration;

import cn.wzpmc.plugins.configuration.INetworkConfiguration;
import lombok.Data;

/**
 * @author wzp
 * @since 2025/3/26 17:29
 * @version 1.0.7
 */
@Data
public class NetworkConfiguration implements INetworkConfiguration {
    /**
     * WebSocket连接URL
     * @since 2025/3/26 17:31 v1.0.7
     */
    private String websocket;
    /**
     * 是否启用连接重试
     * @since 2025/3/26 17:31 v1.0.7
     */
    private Boolean retry;
    /**
     * 重试最大次数（-1为无限）
     * @since 2025/3/26 17:31 v1.0.7
     */
    private Integer maxRetryCount;
    /**
     * 获取重试间隔（单位毫秒）
     * @since 2025/3/26 17:31 v1.0.7
     */
    private Long retryInterval;

    @Override
    public Boolean isRetry() {
        return this.retry;
    }
}
