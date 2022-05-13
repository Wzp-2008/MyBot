package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.enums.MessageSenderType;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/4
 */
public class Console extends MessageSender {
    public Console() {
        super(MessageSenderType.console,0L,"CONSOLE");
    }
}
