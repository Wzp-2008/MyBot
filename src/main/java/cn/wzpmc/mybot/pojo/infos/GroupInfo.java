package cn.wzpmc.mybot.pojo.infos;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
public class GroupInfo {
    @JSONField(name = "group_id")
    private Long groupId;
    @JSONField(name = "group_name")
    private String groupName;
    @JSONField(name = "group_memo")
    private String groupMemo;
    @JSONField(name = "group_create_time")
    private Integer groupCreateTime;
    @JSONField(name = "group_level")
    private Integer groupLevel;
    @JSONField(name = "member_count")
    private Integer memberCount;
    @JSONField(name = "max_member_count")
    private Integer maxMemberCount;
}
