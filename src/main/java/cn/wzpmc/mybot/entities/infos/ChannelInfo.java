package cn.wzpmc.mybot.entities.infos;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/7/2 20:10
 */
@Data
@EqualsAndHashCode
public class ChannelInfo {
    @JSONField(name = "owner_guild_id")
    private String ownerGuildId;
    @JSONField(name = "channel_id")
    private String channelId;
    @JSONField(name = "channel_type")
    private Integer channelType;
    @JSONField(name = "channel_name")
    private String channelName;
    @JSONField(name = "create_time")
    private Long createTime;
    @JSONField(name = "creator_tiny_id")
    private String creatorTinyId;
    @JSONField(name = "talk_permission")
    private Integer talkPermission;
    @JSONField(name = "visible_type")
    private Integer visibleType;
    @JSONField(name = "current_slow_mode")
    private Integer currentSlowMode;
    @JSONField(name = "slow_modes")
    private List<SlowModeInfo> slowModes;

    @JSONCreator
    public ChannelInfo(@JSONField(name = "owner_guild_id")
                       String ownerGuildId,
                       @JSONField(name = "channel_id")
                       String channelId,
                       @JSONField(name = "channel_type")
                       Integer channelType,
                       @JSONField(name = "channel_name")
                       String channelName,
                       @JSONField(name = "create_time")
                       Long createTime,
                       @JSONField(name = "creator_tiny_id")
                       String creatorTinyId,
                       @JSONField(name = "talk_permission")
                       Integer talkPermission,
                       @JSONField(name = "visible_type")
                       Integer visibleType,
                       @JSONField(name = "current_slow_mode")
                       Integer currentSlowMode,
                       @JSONField(name = "slow_modes")
                       JSONArray slowModes) {
        this.ownerGuildId = ownerGuildId;
        this.channelId = channelId;
        this.channelType = channelType;
        this.channelName = channelName;
        this.createTime = createTime;
        this.creatorTinyId = creatorTinyId;
        this.talkPermission = talkPermission;
        this.visibleType = visibleType;
        this.currentSlowMode = currentSlowMode;
        this.slowModes = slowModes.toList(SlowModeInfo.class);
    }
}
