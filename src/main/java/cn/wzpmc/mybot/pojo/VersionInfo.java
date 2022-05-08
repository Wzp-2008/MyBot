package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/8
 */
@Data
public class VersionInfo {
    @JSONField(name="app_name")
    private String appName;
    @JSONField(name="app_version")
    private String appVersion;
    @JSONField(name="app_full_name")
    private String appFullName;
    @JSONField(name="protocol_version")
    private String protocolVersion;
    @JSONField(name="coolq_edition")
    private String coolqEdition;
    @JSONField(name="coolq_directory")
    private String coolqDirectroy;
    @JSONField(name="go-cqhttp")
    private Boolean goCqhttp;
    @JSONField(name="plugin_version")
    private String pluginVersion;
    @JSONField(name="plugin_build_number")
    private Integer pluginBuildNumber;
    @JSONField(name="plugin_build_configuration")
    private String pluginBuildConfiguration;
    @JSONField(name="runtime_version")
    private String runtimeVersion;
    @JSONField(name="runtime_os")
    private String runtimeOs;
    @JSONField(name="version")
    private String version;
    @JSONField(name="protocol")
    private Integer protocol;
}
