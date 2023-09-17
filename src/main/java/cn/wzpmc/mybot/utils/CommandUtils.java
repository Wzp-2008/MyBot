package cn.wzpmc.mybot.utils;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.Main;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.entities.cq.At;
import cn.wzpmc.mybot.entities.events.group.message.GroupNormalMessageEvent;
import cn.wzpmc.mybot.entities.events.privatemessage.PrivateFriendMessageEvent;
import cn.wzpmc.mybot.entities.messages.BaseMessage;
import cn.wzpmc.mybot.entities.messages.GroupMessage;
import cn.wzpmc.mybot.entities.messages.PrivateMessage;
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
    public static String[] parseCommand(JSONObject data, EventIdentifier identifier, Bot bot) {
        String[] commandBody = null;
        if (identifier.equals(EventUtils.groupNormalMessage)){
            GroupNormalMessageEvent groupNormalMessageEvent = new GroupNormalMessageEvent(data);
            String message = groupNormalMessageEvent.getMessage();
            String atString = bot.at.toString();
            if (message.startsWith(atString)) {
                message = message.replaceFirst("\\[CQ:at,qq=.*?]", "");
                if (message.startsWith(" /")) {
                    message = message.replaceFirst(" /", "");
                    commandBody = message.split(" ");
                }
            }
        }else if (identifier.equals(EventUtils.privateFriendMessage)) {
            PrivateFriendMessageEvent privateFriendMessageEvent = new PrivateFriendMessageEvent(data);
            String message = privateFriendMessageEvent.getMessage();
            if (message.startsWith("/")) {
                message = message.replaceFirst("/", "");
                commandBody = message.split(" ");
            }
        }
        return commandBody;
    }

    public static void handlerCommand(Bot bot, EventIdentifier identifier, String[] command, JSONObject data, Logger log) {
        boolean isGroupNormalMessage = identifier.equals(EventUtils.groupNormalMessage);
        boolean isPrivateFriendMessage = identifier.equals(EventUtils.privateFriendMessage);
        String rawCommand = '/' + String.join(" ", command);
        GroupNormalMessageEvent groupNormalMessageEvent = null;
        PrivateFriendMessageEvent privateFriendMessageEvent = null;

        if (isGroupNormalMessage) {
            groupNormalMessageEvent = new GroupNormalMessageEvent(data);
        }
        if (isPrivateFriendMessage) {
            privateFriendMessageEvent = new PrivateFriendMessageEvent(data);
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
            BaseMessage rawMessage = new PrivateMessage();
            if (isPrivateFriendMessage) {
                sender = privateFriendMessageEvent.getSender();
                rawMessage = data.to(PrivateMessage.class);
            }
            if (isGroupNormalMessage) {
                sender = groupNormalMessageEvent.getSender();
                rawMessage = data.to(GroupMessage.class);
            }
            boolean b = executor.onCommand(Arrays.copyOfRange(command, 1, command.length), sender, finalC, rawMessage);
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
