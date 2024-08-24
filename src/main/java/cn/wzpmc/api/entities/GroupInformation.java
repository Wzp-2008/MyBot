package cn.wzpmc.api.entities;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 群信息
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 19:15
 */
@Data
@AllArgsConstructor
public class GroupInformation {
    /**
     * 群号
     * @since 2024/8/25 00:00 v0.0.6-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * 群名称
     * @since 2024/8/25 00:00 v0.0.6-dev
     */
    @JSONField(name = "group_name")
    private String groupName;
    /**
     * 成员数
     * @since 2024/8/25 00:00 v0.0.6-dev
     */
    @JSONField(name = "member_count")
    private Integer memberCount;
    /**
     * 最大成员数（群容量）
     * @since 2024/8/25 00:00 v0.0.6-dev
     */
    @JSONField(name = "max_member_count")
    private Integer maxMemberCount;
}
