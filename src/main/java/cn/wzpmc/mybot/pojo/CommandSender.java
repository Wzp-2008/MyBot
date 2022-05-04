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
    private final long id;
    private final String nickname;
    public CommandSender(CommandSenderType type,long id,String nickname){
        this.type = type;
        this.id = id;
        this.nickname = nickname;
    }
    public boolean isConsole(){
        return CommandSenderType.console.equals(this.type);
    }
}
