package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.enums.CommandSenderType;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/4
 */
public class Console extends CommandSender{
    public Console() {
        super(CommandSenderType.console,0L,"CONSOLE");
    }
}
