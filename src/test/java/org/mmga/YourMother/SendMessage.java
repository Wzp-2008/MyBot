package org.mmga.YourMother;

import cn.wzpmc.mybot.Event.GroupMessageEvent;
import cn.wzpmc.mybot.annotations.EventHandler;
import cn.wzpmc.mybot.interfaces.EventExecutor;
import cn.wzpmc.mybot.pojo.GroupMessage;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 * @date 2022/4/30
 */
public class SendMessage implements EventExecutor {
    @EventHandler
    public static void OnSendMessage(GroupMessageEvent event){
        GroupMessage message = event.getMessage();
        ConcurrentHashMap<Long, String> users = Nm.users;
        long id = message.getSender().getId();
        String orDefault = users.getOrDefault(id, "");
        if(!"".equals(orDefault)){
            message.reply(orDefault);
        }
    }
}
