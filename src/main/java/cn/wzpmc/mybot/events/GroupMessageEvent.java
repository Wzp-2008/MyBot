package cn.wzpmc.mybot.events;

import cn.wzpmc.mybot.pojo.messages.GroupMessage;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageEvent extends Event{
    private GroupMessage message;

    public GroupMessageEvent(JSONObject object){
        super("GroupMessageEvent");
        this.message = new GroupMessage(object);
    }
}
