package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.enums.MessageType;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/13
 */
public class ConsoleMessage extends Message{
    public ConsoleMessage(String content,Console console) {
        super(MessageType.console,content,console);
    }
}
