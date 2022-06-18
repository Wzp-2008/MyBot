package cn.wzpmc.mybot.entities.events.privatemessage;

import cn.wzpmc.mybot.entities.events.Event;
import cn.wzpmc.mybot.entities.users.PrivateUser;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 7:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class PrivateMessageEvent extends Event {
    private Integer messageId;
    private Long userId;
    private String message;
    private String rawMessage;
    private Integer font;
    private PrivateUser sender;

    public PrivateMessageEvent(String eventName, JSONObject data) {
        super(eventName, data);
        this.messageId = data.getInteger("message_id");
        this.userId = data.getLong("user_id");
        this.message = data.getString("message");
        this.rawMessage = data.getString("raw_message");
        this.font = data.getInteger("font");
        this.sender = data.getJSONObject("sender").toJavaObject(PrivateUser.class);
    }
}
