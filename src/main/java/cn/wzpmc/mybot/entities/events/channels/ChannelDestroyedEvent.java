package cn.wzpmc.mybot.entities.events.channels;

import cn.wzpmc.mybot.entities.infos.ChannelInfo;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created On 2022/7/5 7:50
 *
 * @author wzp
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelDestroyedEvent extends ChannelEvent {
    @JSONField(name = "user_id")
    private Long userId;
    @JSONField(name = "operator_id")
    private String operatorId;
    @JSONField(name = "channel_info")
    private ChannelInfo channelInfo;

    public ChannelDestroyedEvent(JSONObject data) {
        super("ChannelDestroyedEvent", data);
        this.userId = data.getLong("user_id");
        this.operatorId = data.getString("operator_id");
        this.channelInfo = data.getObject("channel_info", ChannelInfo.class);
    }
}
