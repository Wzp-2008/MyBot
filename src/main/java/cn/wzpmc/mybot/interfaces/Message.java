package cn.wzpmc.mybot.interfaces;

import cn.wzpmc.mybot.enums.MessageType;
import cn.wzpmc.mybot.pojo.MessageSender;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/10
 */
@Data
@NoArgsConstructor
public abstract class Message {
    private MessageType type;
    private String content;
    private MessageSender sender;
    public Message(MessageType type,String content,MessageSender sender){
        this.type = type;
        this.content = content;
        this.sender = sender;
    }

    /**
     * 回复此消息
     * @param content 内容
     */
    public abstract void reply(String content);
}
