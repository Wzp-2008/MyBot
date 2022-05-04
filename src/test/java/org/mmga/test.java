package org.mmga;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.interfaces.CommandExecutor;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;
import cn.wzpmc.mybot.pojo.Command;
import cn.wzpmc.mybot.pojo.CommandSender;
import cn.wzpmc.mybot.pojo.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ArrayList;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/30
 */
public class test extends MyBotPlugin {
    @Override
    public boolean onLoad() {
        CommandExecutor executor = (command, commandSender) -> {
            if(commandSender.isConsole()){
                System.out.println("console");
            }else{
                User user = (User) commandSender;
                System.out.println(user);
            }
            return true;
        };
        Bot bot = this.getBot();
        bot.registerCommandWithConsole(getCommand("test"),executor);
        return true;

    }
}
