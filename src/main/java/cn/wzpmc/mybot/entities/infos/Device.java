package cn.wzpmc.mybot.entities.infos;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
public class Device {
    @JSONField(name="app_id")
    private Long appId;
    @JSONField(name="device_name")
    private String deviceName;
    @JSONField(name="device_kind")
    private String deviceKind;
}
