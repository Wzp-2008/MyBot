package cn.wzpmc.mybot.pojo.users;

import cn.wzpmc.mybot.enums.MessageSenderType;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
public class MessageSender {
    private final MessageSenderType type;
    private final Long id;
    private final String nickname;
    public MessageSender(MessageSenderType type, Long id, String nickname){
        this.type = type;
        this.id = id;
        this.nickname = nickname;
    }
    private MessageSender(){
        this.type = null;
        this.id = null;
        this.nickname = null;
    }
    public boolean isConsole(){
        return MessageSenderType.console.equals(this.type);
    }
}
