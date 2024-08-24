package cn.wzpmc.api.entities;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * CQ服务端版本信息
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 19:54
 */
@Data
@AllArgsConstructor
public class VersionInformation {
    /**
     * 应用标识，如 mirai-native
     * @since 2024/8/25 00:02 v0.0.6-dev
     */
    @JSONField(name = "app_name")
    private String appName;
    /**
     * 应用版本，如 1.2.3
     * @since 2024/8/25 00:02 v0.0.6-dev
     */
    @JSONField(name = "app_version")
    private String appVersion;
    /**
     * OneBot 标准版本，如 v11
     * @since 2024/8/25 00:02 v0.0.6-dev
     */
    @JSONField(name = "protocol_version")
    private String protocolVersion;
}
