package cn.wzpmc.api.plugins.configuration;

/**
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午3:42
 */
public interface IAuthorizationConfiguration {
    /**
     * @author wzp
     * @since 2024/7/31 上午3:45 v0.0.1-dev
     * @return 是否启用
     */
    boolean isEnable();

    /**
     * @author wzp
     * @since 2024/7/31 上午3:45 v0.0.1-dev
     * @return token
     */
    String getToken();
}
