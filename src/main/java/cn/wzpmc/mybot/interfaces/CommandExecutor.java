package cn.wzpmc.mybot.interfaces;

import cn.wzpmc.mybot.pojo.Command;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/3/31
 */
public interface CommandExecutor {
    /**
     * 运行指令
     * @param command 指令内容
     * @return 指令是否执行成功
     */
    boolean execute(Command command);
}
