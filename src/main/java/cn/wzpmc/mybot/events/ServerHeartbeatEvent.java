package cn.wzpmc.mybot.events;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerHeartbeatEvent extends Event{
    private JSONObject data;
    public ServerHeartbeatEvent(JSONObject data) {
        super("ServerHeartBeatEvent");
        this.data = data;
    }
}
