package cn.wzpmc.mybot.entities.events.channels;

import cn.wzpmc.mybot.entities.infos.ChannelInfo;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/7/2 20:22
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelUpdatedEvent extends ChannelEvent {
    @JSONField(name = "user_id")
    private Long userId;
    @JSONField(name = "operator_id")
    private String operatorId;
    @JSONField(name = "old_info")
    private ChannelInfo oldInfo;
    @JSONField(name = "new_info")
    private ChannelInfo newInfo;

    public ChannelUpdatedEvent(JSONObject data) {
        super("ChannelUpdatedEvent", data);
        this.userId = data.getLong("user_id");
        this.operatorId = data.getString("operator_id");
        this.newInfo = data.getObject("new_info", ChannelInfo.class);
        this.oldInfo = data.getObject("old_info", ChannelInfo.class);
        System.out.println(this);
    }
}
