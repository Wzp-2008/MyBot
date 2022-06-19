package cn.wzpmc.mybot.entities.messages;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.entities.users.PrivateUser;
import cn.wzpmc.mybot.enums.MessageType;
import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/19 9:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PrivateMessage extends BaseMessage {
    private Integer messageId;
    private Long userId;
    private String message;
    private String rawMessage;
    private Integer font;
    private PrivateUser sender;
    private Integer tempSource;

    @JSONCreator
    public PrivateMessage(@JSONField(name = "message_id") Integer messageId,
                          @JSONField(name = "user_id") Long userId,
                          String message,
                          @JSONField(name = "raw_message") String rawMessage,
                          Integer font,
                          PrivateUser sender,
                          @JSONField(name = "temp_source") Integer tempSource) {
        super(MessageType.privateMessage, message, sender);
        this.messageId = messageId;
        this.userId = userId;
        this.message = message;
        this.rawMessage = rawMessage;
        this.font = font;
        this.sender = sender;
        this.tempSource = tempSource;

    }

    @Override
    public Integer reply(String content, Bot bot) {
        return bot.getApi().sendPrivateMessage(this.userId, Long.valueOf(this.tempSource), content);
    }
}
