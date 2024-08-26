package cn.wzpmc.entities;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * QQ认证信息
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 19:46
 */
@Data
@AllArgsConstructor
public class CredentialsInformation {
    /**
     * Cookies
     *
     * @since 2024/8/24 23:59 v0.0.6-dev
     */
    private String cookies;
    /**
     * CSRF Token
     *
     * @since 2024/8/24 23:59 v0.0.6-dev
     */
    @JSONField(name = "csrf_token")
    private Integer csrfToken;
}
