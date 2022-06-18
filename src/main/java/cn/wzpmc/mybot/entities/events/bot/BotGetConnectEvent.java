package cn.wzpmc.mybot.entities.events.bot;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * 当机器人创建连接成功时激活
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BotGetConnectEvent extends Event {
    public BotGetConnectEvent(JSONObject data) {
        super("BotGetConnectEvent", data);
    }
}
