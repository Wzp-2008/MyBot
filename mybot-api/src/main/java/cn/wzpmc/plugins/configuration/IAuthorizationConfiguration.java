package cn.wzpmc.plugins.configuration;

/**
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午3:42
 */
public interface IAuthorizationConfiguration {
    /**
     * @return 是否启用
     * @author wzp
     * @since 2024/7/31 上午3:45 v0.0.1-dev
     */
    boolean isEnable();

    /**
     * @return token
     * @author wzp
     * @since 2024/7/31 上午3:45 v0.0.1-dev
     */
    String getToken();
}
