package cn.wzpmc.mybot.entities.events.other;

import cn.wzpmc.mybot.entities.events.Event;
import cn.wzpmc.mybot.entities.infos.Device;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OtherClientStatusChangeEvent extends Event {
    private Device device;
    private Boolean online;

    public OtherClientStatusChangeEvent(JSONObject data) {
        super("OtherClientStatusChangeEvent", data);
        this.device = data.getJSONObject("device").to(Device.class);
        this.online = data.getBoolean("online");
    }
}
