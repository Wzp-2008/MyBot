package cn.wzpmc.configuration;

import cn.wzpmc.plugins.configuration.IConfiguration;
import lombok.Data;

/**
 * 配置类
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/30 下午11:48
 */
@Data
public class Configuration implements IConfiguration {
    /**
     * WebSocket连接URL
     *
     * @since 2024/7/30 下午11:48 v0.0.1-dev
     */
    private String websocket;

    /**
     * 命令前缀
     * @since 2025/2/4 13:45 v1.0.5
     */
    private String commandPrefix;
    /**
     * 通信验证
     *
     * @since 2024/7/30 下午11:49 v0.0.1-dev
     */
    private AuthorizationConfiguration authorization;
    /**
     * 失败消息提示
     *
     * @since 2024/7/30 下午11:49 v0.0.1-dev
     */
    private FallbackConfiguration fallback;
    /**
     * 好友配置
     *
     * @since 2024/8/30 15:43 v1.0.3
     */
    private FriendConfiguration friend;

    /**
     * 群聊相关配置
     *
     * @since 2024/8/31 23:57 v1.0.3
     */
    private GroupConfiguration group;
}
