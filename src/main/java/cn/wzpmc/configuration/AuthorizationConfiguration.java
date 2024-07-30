package cn.wzpmc.configuration;

import cn.wzpmc.api.plugins.configuration.IAuthorizationConfiguration;
import lombok.Data;

/**
 * 通信验证配置类
 * @author wzp
 * @since 2024/7/30 下午11:50
 * @version 0.0.1-dev
 */
@Data
public class AuthorizationConfiguration implements IAuthorizationConfiguration {
    /**
     * 是否启用
     * @since 2024/7/30 下午11:50 v0.0.1-dev
     */
    private boolean enable;
    /**
     * token
     * @since 2024/7/30 下午11:50 v0.0.1-dev
     */
    private String token;
}
