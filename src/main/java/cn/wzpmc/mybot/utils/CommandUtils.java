package cn.wzpmc.mybot.utils;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.Main;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.entities.cq.At;
import cn.wzpmc.mybot.entities.events.group.message.GroupNormalMessageEvent;
import cn.wzpmc.mybot.entities.events.privatemessage.PrivateFriendMessageEvent;
import cn.wzpmc.mybot.entities.users.MessageSender;
import cn.wzpmc.mybot.entities.utils.Command;
import cn.wzpmc.mybot.entities.utils.EventIdentifier;
import cn.wzpmc.mybot.interfaces.CommandExecutor;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.Map;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/19 0:18
 */
public class CommandUtils {
    public static String[] isCommand(JSONObject data, EventIdentifier identifier, Bot bot) {
        boolean isGroupNormalMessage = identifier.equals(EventUtils.groupNormalMessage);
        boolean isPrivateFriendMessage = identifier.equals(EventUtils.privateFriendMessage);
        boolean isCommand;
        String[] command = null;
        GroupNormalMessageEvent groupNormalMessageEvent;
        String rawCommand;
        if (isGroupNormalMessage) {
            At at = bot.at;
            String atString = at.toString();
            atString += " /";
            groupNormalMessageEvent = new GroupNormalMessageEvent(data);
            String message = groupNormalMessageEvent.getMessage();
            isCommand = message.contains(atString);
            if (isCommand) {
                rawCommand = message.replace(atString, "");
                command = rawCommand.split(" ");
            }
        }
        PrivateFriendMessageEvent privateFriendMessageEvent;
        if (isPrivateFriendMessage) {
            privateFriendMessageEvent = new PrivateFriendMessageEvent(data);
            String message = privateFriendMessageEvent.getMessage();
            isCommand = message.contains("/");
            if (isCommand) {
                rawCommand = message.replace("/", "");
                command = rawCommand.split(" ");
            }
        }
        return command;
    }

    public static void handlerCommand(Bot bot, EventIdentifier identifier, String[] command, JSONObject data, Logger log) {
        boolean isGroupNormalMessage = identifier.equals(EventUtils.groupNormalMessage);
        boolean isPrivateFriendMessage = identifier.equals(EventUtils.privateFriendMessage);
        GroupNormalMessageEvent groupNormalMessageEvent = null;
        PrivateFriendMessageEvent privateFriendMessageEvent = null;
        String rawCommand = "";
        if (isGroupNormalMessage) {
            groupNormalMessageEvent = new GroupNormalMessageEvent(data);
            At at = bot.at;
            String atString = at.toString();
            rawCommand = groupNormalMessageEvent.getMessage().replace(atString, "");
        }
        if (isPrivateFriendMessage) {
            privateFriendMessageEvent = new PrivateFriendMessageEvent(data);
            rawCommand = privateFriendMessageEvent.getMessage().replace("/", "");
        }
        Map<Command, CommandExecutor> commands = bot.getCommands();
        boolean hasCommand = false;
        CommandExecutor executor = null;
        Command finalC = null;
        for (Command c : commands.keySet()) {
            if (c.getName().equals(command[0])) {
                hasCommand = true;
                executor = commands.get(c);
                finalC = c;
                break;
            }
        }
        MyBotApi api = bot.getApi();
        if (!hasCommand) {
            String notFoundCommandMessage = Main.getConfig().getProperty("not_found_command_message");
            if (isPrivateFriendMessage) {
                api.sendPrivateMessage(privateFriendMessageEvent.getUserId(), 0L, notFoundCommandMessage);
            }
            if (isGroupNormalMessage) {
                api.sendGroupMessage(groupNormalMessageEvent.getGroupId(), new At(groupNormalMessageEvent.getUserId()) + notFoundCommandMessage);
            }
        } else {
            MessageSender sender = null;
            if (isPrivateFriendMessage) {
                sender = privateFriendMessageEvent.getSender();
            }
            if (isGroupNormalMessage) {
                sender = groupNormalMessageEvent.getSender();
            }
            boolean b = executor.onCommand(Arrays.copyOfRange(command, 1, command.length), sender, finalC);
            if (!b) {
                String failedRunMessage = Main.getConfig().getProperty("failed_run_message");
                if (isPrivateFriendMessage) {
                    api.sendPrivateMessage(privateFriendMessageEvent.getUserId(), 0L, failedRunMessage);
                }
                if (isGroupNormalMessage) {
                    api.sendGroupMessage(groupNormalMessageEvent.getGroupId(), new At(groupNormalMessageEvent.getUserId()) + failedRunMessage);
                }
            }
        }
        if (isPrivateFriendMessage) {
            log.info("用户{}执行了指令：{}", privateFriendMessageEvent.getUserId(), rawCommand);
        }
        if (isGroupNormalMessage) {
            log.info("用户{}在群{}执行了指令：{}", groupNormalMessageEvent.getUserId(), groupNormalMessageEvent.getGroupId(), rawCommand);
        }
    }
}
