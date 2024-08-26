package cn.wzpmc.entities;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 群荣耀信息
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 19:36
 */
@Data
@AllArgsConstructor
public class HonorInformation {
    /**
     * 群号
     *
     * @since 2024/8/24 23:17 v0.0.6-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * 当前龙王，仅 type 为 talkative 或 all 时有数据
     *
     * @since 2024/8/24 23:17 v0.0.6-dev
     */
    @JSONField(name = "current_talkative")
    private HonorTalkative currentTalkative;
    /**
     * 历史龙王，仅 type 为 talkative 或 all 时有数据
     *
     * @since 2024/8/24 23:17 v0.0.6-dev
     */
    @JSONField(name = "talkative_list")
    private List<GroupHonorUser> talkativeList;
    /**
     * 群聊之火，仅 type 为 performer 或 all 时有数据
     *
     * @since 2024/8/24 23:18 v0.0.6-dev
     */
    @JSONField(name = "performer_list")
    private List<GroupHonorUser> performerList;
    /**
     * 群聊炽焰，仅 type 为 legend 或 all 时有数据
     *
     * @since 2024/8/24 23:18 v0.0.6-dev
     */
    @JSONField(name = "legend_list")
    private List<GroupHonorUser> legendList;
    /**
     * 冒尖小春笋，仅 type 为 strong_newbie 或 all 时有数据
     *
     * @since 2024/8/24 23:18 v0.0.6-dev
     */
    @JSONField(name = "strong_newbie_list")
    private List<GroupHonorUser> strongNewbieList;
    /**
     * 快乐之源，仅 type 为 emotion 或 all 时有数据
     *
     * @since 2024/8/24 23:18 v0.0.6-dev
     */
    @JSONField(name = "emotion_list")
    private List<GroupHonorUser> emotionList;
}
