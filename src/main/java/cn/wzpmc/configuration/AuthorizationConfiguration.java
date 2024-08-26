package cn.wzpmc.configuration;

import cn.wzpmc.plugins.configuration.IAuthorizationConfiguration;
import lombok.Data;

/**
 * 通信验证配置类
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/30 下午11:50
 */
@Data
public class AuthorizationConfiguration implements IAuthorizationConfiguration {
    /**
     * 是否启用
     *
     * @since 2024/7/30 下午11:50 v0.0.1-dev
     */
    private boolean enable;
    /**
     * token
     *
     * @since 2024/7/30 下午11:50 v0.0.1-dev
     */
    private String token;
}
