package cn.wzpmc.mybot.entities.events.channels;

import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/7/2 19:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ChannelEvent extends Event {
    @JSONField(name = "guild_id")
    private String guildId;
    @JSONField(name = "channel_id")
    private String channelId;

    public ChannelEvent(String eventName, JSONObject data) {
        super(eventName, data);
        this.guildId = data.getString("guild_id");
        this.channelId = data.getString("channel_id");
    }
}
