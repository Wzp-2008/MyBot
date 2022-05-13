package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.enums.MessageSenderType;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/4
 */
@Data
public class MessageSender {
    private final MessageSenderType type;
    private final long id;
    private final String nickname;
    public MessageSender(MessageSenderType type, long id, String nickname){
        this.type = type;
        this.id = id;
        this.nickname = nickname;
    }
    public boolean isConsole(){
        return MessageSenderType.console.equals(this.type);
    }
}
