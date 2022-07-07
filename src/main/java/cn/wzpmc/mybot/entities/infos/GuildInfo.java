package cn.wzpmc.mybot.entities.infos;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created On 2022/7/5 7:58
 *
 * @author wzp
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode
public class GuildInfo {
    @JSONField(name = "guild_id")
    private String guildId;
    @JSONField(name = "guild_name")
    private String guildName;
    @JSONField(name = "guild_display_id")
    private String guildDisplayId;

    public GuildInfo(@JSONField(name = "guild_id") String guildId,
                     @JSONField(name = "guild_name") String guildName,
                     @JSONField(name = "guild_display_id") String guildDisplayId) {
        this.guildId = guildId;
        this.guildName = guildName;
        this.guildDisplayId = guildDisplayId;
    }
}
