package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.enums.MessageType;
import cn.wzpmc.mybot.interfaces.BaseMessage;
import org.slf4j.Logger;

/**
 * @author wzp
 * @version 1.0.0
 */
public class ConsoleBaseMessage extends BaseMessage {
    public ConsoleBaseMessage(String content, Console console) {
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
