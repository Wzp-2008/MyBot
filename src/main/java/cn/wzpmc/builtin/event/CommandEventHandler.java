package cn.wzpmc.builtin.event;

import cn.wzpmc.entities.user.bot.MyBot;
import cn.wzpmc.events.message.group.GroupMessageEvent;
import cn.wzpmc.events.message.priv.PrivateMessageEvent;
import cn.wzpmc.message.StringMessage;
import cn.wzpmc.plugins.CommandManager;
import cn.wzpmc.plugins.event.EventHandler;
import cn.wzpmc.user.Friend;
import cn.wzpmc.user.IBot;
import cn.wzpmc.user.group.GroupCommandSender;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Pattern;

/**
 * 命令事件处理器
 *
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 13:40
 */
@Log4j2
public class CommandEventHandler {
    @EventHandler
    public void onGroupMessage(GroupMessageEvent event) {
        GroupCommandSender groupCommandSender = GroupCommandSender.of(event);
        IBot instance = MyBot.getInstance();
        String commandPrefix = instance.getConfiguration().getCommandPrefix();
        Long id = instance.getId();
        String message = event.getRawMessage().getMessage();
        Pattern compile = Pattern.compile("\\[CQ:at,qq=" + id + ".*?]\\s*?" + commandPrefix + ".*");
        if (compile.asMatchPredicate().test(message)) {
            CommandManager commandManager = (CommandManager) instance.getCommandManager();
            String commandRaw = message.replaceFirst("\\[CQ:at,qq=[0-9]{10}.*?]\\s*?" + commandPrefix, "");
            log.info("群{}中的用户{}使用了指令{}", groupCommandSender.getGroupId(), groupCommandSender.getId(), commandRaw);
            commandManager.execute(groupCommandSender, commandRaw);
        }
    }

    @EventHandler
    public void onPrivateMessage(PrivateMessageEvent event) {
        Friend sender = event.getSender();
        IBot instance = MyBot.getInstance();
        StringMessage rawMessage = event.getRawMessage();
        String message = rawMessage.getMessage();
        String commandPrefix = instance.getConfiguration().getCommandPrefix();
        if (message.startsWith(commandPrefix)) {
            CommandManager commandManager = (CommandManager) instance.getCommandManager();
            String commandRaw = message.replaceFirst(commandPrefix, "");
            log.info("用户{}使用了指令{}", sender.getId(), commandRaw);
            commandManager.execute(sender, commandRaw);
        }
    }
}
