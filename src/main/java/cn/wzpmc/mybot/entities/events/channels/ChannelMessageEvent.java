package cn.wzpmc.mybot.entities.events.channels;

import cn.wzpmc.mybot.entities.users.ChannelUser;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/7/2 19:26
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelMessageEvent extends ChannelEvent {
    @JSONField(name = "user_id")
    private Long userId;
    @JSONField(name = "message_id")
    private String messageId;
    @JSONField(name = "sender")
    private ChannelUser sender;
    @JSONField(name = "message")
    private String message;

    public ChannelMessageEvent(JSONObject data) {
        super("ChannelMessageEvent", data);
        this.userId = data.getLong("user_id");
        this.messageId = data.getString("message_id");
        this.sender = data.getObject("sender", ChannelUser.class);
        this.message = data.getString("message");
    }
}
