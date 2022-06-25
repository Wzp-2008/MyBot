package cn.wzpmc.mybot.entities.utils;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.interfaces.CommandExecutor;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode
public class Command {
    private final String name;
    private final MyBotPlugin plugin;
    private CommandExecutor executor;

    public Command(String name, MyBotPlugin plugin) {
        this.name = name;
        this.plugin = plugin;
    }

    public void setExecutor(CommandExecutor executor) {
        this.executor = executor;
        Bot bot = plugin.getBot();
        bot.registerCommand(this, executor);
    }
}
