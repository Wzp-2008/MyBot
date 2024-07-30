package cn.wzpmc.configuration;

import cn.wzpmc.api.plugins.configuration.IConfiguration;
import lombok.*;

/**
 * 配置类
 * @author wzp
 * @since 2024/7/30 下午11:48
 * @version 0.0.1-dev
 */
@Data
public class Configuration implements IConfiguration {
    /**
     * WebSocket连接URL
     * @since 2024/7/30 下午11:48 v0.0.1-dev
     */
    private String websocket;
    /**
     * 通信验证
     * @since 2024/7/30 下午11:49 v0.0.1-dev
     */
    private AuthorizationConfiguration authorization;
    /**
     * 失败消息提示
     * @since 2024/7/30 下午11:49 v0.0.1-dev
     */
    private FallbackConfiguration fallback;
}
