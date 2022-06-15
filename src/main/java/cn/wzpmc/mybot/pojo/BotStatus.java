package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
public class BotStatus {
    @JSONField(name="app_initialized")
    private Boolean appInitialized;
    @JSONField(name="app_enabled")
    private Boolean appEnabled;
    @JSONField(name="plugins_good")
    private Boolean pluginsGood;
    @JSONField(name="app_good")
    private Boolean appGood;
    @JSONField(name="online")
    private Boolean online;
    @JSONField(name="good")
    private Boolean good;
    @JSONField(name="stat")
    private Statistics stat;

    public BotStatus(
            @JSONField(name="app_initialized")
            Boolean appInitialized,
            @JSONField(name="app_enabled")
            Boolean appEnabled,
            @JSONField(name="plugins_good")
            Boolean pluginsGood,
            @JSONField(name="app_good")
            Boolean appGood,
            @JSONField(name="online")
            Boolean online,
            @JSONField(name="good")
            Boolean good,
            @JSONField(name="stat")
            JSONObject stat
    ){
        this.appInitialized = appInitialized;
        this.appEnabled = appEnabled;
        this.pluginsGood = pluginsGood;
        this.appGood = appGood;
        this.online = online;
        this.good = good;
        this.stat = stat.toJavaObject(Statistics.class);
    }
}
