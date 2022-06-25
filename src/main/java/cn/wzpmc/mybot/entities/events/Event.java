package cn.wzpmc.mybot.entities.events;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode
public class Event {
    private String eventName;
    private Long time;

    protected Event() {
    }

    public Event(String eventName, JSONObject data) {
        this.eventName = eventName;
        this.time = data.getLong("time");
    }

}
