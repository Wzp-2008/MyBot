package cn.wzpmc.plugins.configuration;

/**
 * 群聊相关配置
 *
 * @author wzp
 * @version 1.0.3
 * @since 2024/8/31 23:55
 */
public interface IGroupConfiguration {
    /**
     * @return 是否自动同意加群邀请
     * @author wzp
     * @since 2024/8/30 15:41 v1.0.3
     */
    Boolean isAutoAccept();
}
