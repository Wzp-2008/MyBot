package cn.wzpmc.mybot.entities.messages;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.entities.users.Console;
import cn.wzpmc.mybot.entities.users.MessageSender;
import cn.wzpmc.mybot.enums.MessageType;
import org.slf4j.Logger;

/**
 * @author wzp
 * @version 1.0.0
 */
public class ConsoleMessage extends BaseMessage {
    public ConsoleMessage(String content, Console console) {
        super(MessageType.console, content, console);
    }

    @Override
    public Integer reply(String content, Bot bot) {
        MessageSender sender = this.getSender();
        Console console = ((Console) sender);
        Logger logger = console.getLogger();
        logger.info(content);
        return 0;
    }
}
