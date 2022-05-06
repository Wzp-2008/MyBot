package cn.wzpmc.mybot.Event;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.pojo.GroupMessage;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/30
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class PrivateMessageEvent extends Event{
    private GroupMessage message;
    private Long time;

    public PrivateMessageEvent(JSONObject object, Bot bot){
        super("GroupMessageEvent");
        this.message = new GroupMessage(object,bot);
        this.time = object.getLong("time");
    }
}
