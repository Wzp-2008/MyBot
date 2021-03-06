package cn.wzpmc.mybot.entities.users;

import cn.wzpmc.mybot.enums.MessageSenderType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode
public class MessageSender {
    private final MessageSenderType type;
    private final Long id;
    private final String nickname;

    public MessageSender(MessageSenderType type, Long id, String nickname) {
        this.type = type;
        this.id = id;
        this.nickname = nickname;
    }

    protected MessageSender() {
        this.type = null;
        this.id = null;
        this.nickname = null;
    }

    public boolean isConsole() {
        return MessageSenderType.console.equals(this.type);
    }
}
