package cn.wzpmc.mybot.interfaces;

import cn.wzpmc.mybot.pojo.users.MessageSender;
import cn.wzpmc.mybot.pojo.utils.Command;

/**
 * @author wzp
 * @version 1.0.0
 */
public interface CommandExecutor {
    /**
     * 运行指令
     * @param command 指令内容
     * @param commandSender 命令发送者
     * @return 指令是否执行成功
     */
    boolean execute(Command command, MessageSender commandSender);
}
