package cn.wzpmc.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * CSRF信息
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 19:45
 */
@Data
@AllArgsConstructor
public class CsrfTokenInformation {
    /**
     * CSRF Token
     * @since 2024/8/24 23:59 v0.0.6-dev
     */
    private Integer token;
}
