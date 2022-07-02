package cn.wzpmc.mybot.entities.events.channels;

import cn.wzpmc.mybot.entities.infos.ReactionInfo;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/7/2 19:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelMessageReactionsUpdatedEvent extends ChannelEvent {
    @JSONField(name = "current_reactions")
    List<ReactionInfo> currentReactions;
    @JSONField(name = "user_id")
    private Long userId;
    @JSONField(name = "message_id")
    private String messageId;

    public ChannelMessageReactionsUpdatedEvent(JSONObject data) {
        super("ChannelMessageReactionsUpdatedEvent", data);
        userId = data.getLong("user_id");
        messageId = data.getString("message_id");
        currentReactions = data.getJSONArray("current_reactions").toJavaList(ReactionInfo.class);
    }
}
