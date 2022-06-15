package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
public class GroupMemberInfo {
    @JSONField(name="group_id")
    private Long groupId;
    @JSONField(name="user_id")
    private Long userId;
    @JSONField(name="nickname")
    private String nickname;
    @JSONField(name="card")
    private String card;
    @JSONField(name="sex")
    private String sex;
    @JSONField(name="age")
    private Integer age;
    @JSONField(name="area")
    private String area;
    @JSONField(name="join_time")
    private Integer joinTime;
    @JSONField(name="last_sent_time")
    private Integer lastSentTime;
    @JSONField(name="level")
    private String level;
    @JSONField(name="role")
    private String role;
    @JSONField(name="unfriendly")
    private Boolean unfriendly;
    @JSONField(name="title")
    private String title;
    @JSONField(name="title_expire_time")
    private Long titleExpireTime;
    @JSONField(name="card_changeable")
    private Boolean cardChangeAble;
    @JSONField(name="shut_up_timestamp")
    private Long shutUpTimeStamp;
}
