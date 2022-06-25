package cn.wzpmc.mybot.entities.messages;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.entities.users.MessageSender;
import cn.wzpmc.mybot.enums.MessageType;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
public abstract class BaseMessage {
    private MessageType type;
    private String content;
    private MessageSender sender;

    public BaseMessage(MessageType type, String content, MessageSender sender) {
        this.type = type;
        this.content = content;
        this.sender = sender;
    }

    /**
     * 回复此消息
     *
     * @param content 内容
     * @param bot     传入一个机器人对象
     * @return 消息id
     */
    public abstract Integer reply(String content, Bot bot);
}
