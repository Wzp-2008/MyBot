package cn.wzpmc.mybot.entities.events.group.bot;

import cn.wzpmc.mybot.entities.events.Event;
import cn.wzpmc.mybot.entities.infos.BotStatus;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServerHeartbeatEvent extends Event {
    private Long time;
    private BotStatus stat;

    public ServerHeartbeatEvent(JSONObject data) {
        super("ServerHeartBeatEvent", data);
        this.stat = data.getJSONObject("status").toJavaObject(BotStatus.class);
    }
}
