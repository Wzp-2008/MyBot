package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.enums.CommandSenderType;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/4
 */
@Data
public class CommandSender {
    private final CommandSenderType type;
    public CommandSender(CommandSenderType type){
        this.type = type;
    }
}
