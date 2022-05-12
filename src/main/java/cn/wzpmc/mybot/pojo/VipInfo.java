package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/12
 */
@Data
public class VipInfo {
    @JSONField(name="user_id")
    private Long userId;
    @JSONField(name="nickname")
    private String nickname;
    @JSONField(name="level")
    private Long level;
    @JSONField(name="level_speed")
    private Double levelSpeed;
    @JSONField(name="vip_level")
    private String vipLevel;
    @JSONField(name="vip_growth_speed")
    private Long vipGrowthSpeed;
    @JSONField(name="vip_growth_total")
    private Long vipGrowthTotal;
}
