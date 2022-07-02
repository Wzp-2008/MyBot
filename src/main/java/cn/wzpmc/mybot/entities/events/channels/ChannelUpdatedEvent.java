package cn.wzpmc.mybot.entities.events.channels;

import cn.wzpmc.mybot.entities.infos.ChannelInfo;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created On 2022/7/2 20:22
 * 此事件在wzp的机器上测试时无法触发（cq无提示），请谨慎使用，若发现bug请提交issue（带上日志和当前go-cqhttp版本）
 *
 * @author wzp
 * @version 1.0.0
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
    }
}
