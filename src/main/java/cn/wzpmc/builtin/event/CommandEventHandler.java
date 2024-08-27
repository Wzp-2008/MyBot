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

import java.util.regex.Pattern;

/**
 * 命令事件处理器
 *
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 13:40
 */
public class CommandEventHandler {
    @EventHandler
    public void onGroupMessage(GroupMessageEvent event) {
        GroupCommandSender groupCommandSender = GroupCommandSender.of(event);
        IBot instance = MyBot.getInstance();
        Long id = instance.getId();
        String message = event.getRawMessage().getMessage();
        Pattern compile = Pattern.compile("\\[CQ:at,qq=" + id + ".*?]\\s*?/.*");
        if (compile.asMatchPredicate().test(message)) {
            CommandManager commandManager = (CommandManager) instance.getCommandManager();
            commandManager.execute(groupCommandSender, message.replaceFirst("\\[CQ:at,qq=[0-9]{10}.*?]\\s*?/", ""));
        }
    }

    @EventHandler
    public void onPrivateMessage(PrivateMessageEvent event) {
        Friend sender = event.getSender();
        IBot instance = MyBot.getInstance();
        StringMessage rawMessage = event.getRawMessage();
        String message = rawMessage.getMessage();
        if (message.startsWith("/")) {
            CommandManager commandManager = (CommandManager) instance.getCommandManager();
            commandManager.execute(sender, message.replaceFirst("/", ""));
        }
    }
}