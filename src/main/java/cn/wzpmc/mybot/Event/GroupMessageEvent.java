package cn.wzpmc.mybot.Event;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.pojo.GroupMessage;
import com.alibaba.fastjson.JSONObject;
import lombok.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/2
 */
@EqualsAndHashCode(callSuper = true)
@ToString
@Data
public class GroupMessageEvent extends Event{
    private GroupMessage message;

    public GroupMessageEvent(JSONObject object, Bot bot){
        super("GroupMessageEvent");
        this.message = new GroupMessage(object,bot);
    }
}
