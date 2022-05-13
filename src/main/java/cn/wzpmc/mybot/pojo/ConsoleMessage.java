package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.enums.MessageType;
import cn.wzpmc.mybot.interfaces.Message;
import org.slf4j.Logger;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/13
 */
public class ConsoleMessage extends Message {
    public ConsoleMessage(String content,Console console) {
        super(MessageType.console,content,console);
    }

    @Override
    public void reply(String content) {
        MessageSender sender = this.getSender();
        Console console = ((Console) sender);
        Logger logger = console.getLogger();
        logger.info(content);
    }
}
