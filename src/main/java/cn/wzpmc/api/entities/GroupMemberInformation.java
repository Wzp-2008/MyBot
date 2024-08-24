package cn.wzpmc.api.entities;

import cn.wzpmc.api.user.Sex;
import cn.wzpmc.api.user.permission.Permissions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * 群成员信息
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 19:22
 */
@Data
@AllArgsConstructor
public class GroupMemberInformation {
    /**
     * 群号
     * @since 2024/8/24 19:29 v0.0.6-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * QQ 号
     * @since 2024/8/24 19:29 v0.0.6-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
    /**
     * 昵称
     * @since 2024/8/24 19:29 v0.0.6-dev
     */
    private String nickname;
    /**
     * 群名片／备注
     * @since 2024/8/24 19:29 v0.0.6-dev
     */
    private String card;
    /**
     * 性别
     * @since 2024/8/24 19:29 v0.0.6-dev
     */
    private Sex sex;
    /**
     * 年龄
     * @since 2024/8/24 19:29 v0.0.6-dev
     */
    private Integer age;
    /**
     * 地区
     * @since 2024/8/24 19:30 v0.0.6-dev
     */
    private String area;
    /**
     * 加群时间
     * @since 2024/8/24 19:30 v0.0.6-dev
     */
    @JSONField(name = "join_time")
    private Date joinTime;
    /**
     * 最后发言时间
     * @since 2024/8/24 19:30 v0.0.6-dev
     */
    @JSONField(name = "last_sent_time")
    private Date lastSentTime;
    /**
     * 成员等级
     * @since 2024/8/24 19:30 v0.0.6-dev
     */
    private String level;
    /**
     * 角色
     * @since 2024/8/24 19:30 v0.0.6-dev
     */
    private Permissions role;
    /**
     * 是否不良记录成员
     * @since 2024/8/24 19:30 v0.0.6-dev
     */
    private boolean unfriendly;
    /**
     * 专属头衔
     * @since 2024/8/24 19:30 v0.0.6-dev
     */
    private String title;
    /**
     * 专属头衔过期时间
     * @since 2024/8/24 19:30 v0.0.6-dev
     */
    @JSONField(name = "title_expire_time")
    private Date titleExpireTime;
    /**
     * 是否允许修改群名片
     * @since 2024/8/24 19:30 v0.0.6-dev
     */
    @JSONField(name = "card_changeable")
    private boolean cardChangeable;
}
