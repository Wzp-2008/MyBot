package cn.wzpmc.mybot.utils;

import cn.wzpmc.mybot.entities.messages.GroupMessage;
import cn.wzpmc.mybot.entities.utils.Command;
import cn.wzpmc.mybot.interfaces.CommandExecutor;
import org.slf4j.Logger;

import java.util.Map;
import java.util.Properties;

import static cn.wzpmc.mybot.Main.bot;

/**
 * @author wzp
 * @version 1.0.0
 */
public class CommandUtils {
    public static void runCommand(String content, String s, GroupMessage gMessage, Logger log, Properties config) {
        Command command = new Command(content.replace(s, ""), gMessage);
        Long id = gMessage.getSender().getId();
        String commandContent = content.replace(bot.at.toString(), "");
        log.info("用户" + id + "运行了命令：" + commandContent);
        Map<Command, CommandExecutor> commands = bot.getCommands();
        CommandExecutor commandExecutor = commands.get(command);
        if (commandExecutor != null) {
            boolean execute = commandExecutor.execute(command, gMessage.getSender());
            if (!execute) {
                Integer failedRunMessage = gMessage.reply(config.getProperty("failed_run_message"), bot);
                log.info("用户{}运行指令{}时出现错误，返回消息id={}", id, commandContent, failedRunMessage);
            }
        } else {
            Integer notFoundCommandMessage = gMessage.reply(config.getProperty("not_found_command_message"), bot);
            log.info("用户{}运行的指令{}不存在，返回消息id={}", id, commandContent, notFoundCommandMessage);
        }
    }
}
