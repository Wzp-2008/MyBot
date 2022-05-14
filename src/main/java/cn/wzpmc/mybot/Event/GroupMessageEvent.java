package cn.wzpmc.mybot.Event;

import cn.wzpmc.mybot.pojo.GroupMessage;
import com.alibaba.fastjson2.JSONObject;
import lombok.*;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/2
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
