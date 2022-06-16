package cn.wzpmc.mybot.events;

import cn.wzpmc.mybot.pojo.messages.GroupMessage;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class PrivateMessageEvent extends Event{
    private GroupMessage message;
    private Long time;

    public PrivateMessageEvent(JSONObject object){
        super("GroupMessageEvent");
        this.message = new GroupMessage(object);
        this.time = object.getLong("time");
    }
}
