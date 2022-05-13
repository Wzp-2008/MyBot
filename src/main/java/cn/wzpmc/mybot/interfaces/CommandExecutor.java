package cn.wzpmc.mybot.interfaces;

import cn.wzpmc.mybot.pojo.Command;
import cn.wzpmc.mybot.pojo.MessageSender;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/3/31
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
