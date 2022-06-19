package cn.wzpmc.mybot.interfaces;

import cn.wzpmc.mybot.entities.messages.BaseMessage;
import cn.wzpmc.mybot.entities.users.MessageSender;
import cn.wzpmc.mybot.entities.utils.Command;

/**
 * @author wzp
 * @version 1.0.0
 */
public interface CommandExecutor {
    /**
     * 运行指令
     *
     * @param args       指令的参数
     * @param sender     指令发送者
     * @param command    命令源
     * @param rawMessage 原始消息
     * @return 指令运行是否成功
     */
    boolean onCommand(String[] args, MessageSender sender, Command command, BaseMessage rawMessage);
}
