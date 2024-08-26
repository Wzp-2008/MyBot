package cn.wzpmc.user.group;

import cn.wzpmc.user.IUser;
import cn.wzpmc.user.Sex;
import cn.wzpmc.user.permission.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 群内用户
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午8:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupUser extends IUser {
    /**
     * 群名片/备注
     *
     * @since 2024/8/1 下午8:54 v0.0.2-dev
     */
    private String card;
    /**
     * 地区
     *
     * @since 2024/8/1 下午8:54 v0.0.2-dev
     */
    private String area;
    /**
     * 成员等级
     *
     * @since 2024/8/1 下午8:53 v0.0.2-dev
     */
    private String level;
    /**
     * 群角色
     *
     * @since 2024/8/1 下午8:53 v0.0.2-dev
     */
    private GroupUserRole role;
    /**
     * 专属头衔
     *
     * @since 2024/8/1 下午8:53 v0.0.2-dev
     */
    private String title;

    public GroupUser(Long id, String name, Permissions permissions, String nickname, Sex sex, Integer age, String card, String area, String level, GroupUserRole role, String title) {
        super(id, name, permissions, nickname, sex, age);
        this.card = card;
        this.area = area;
        this.level = level;
        this.role = role;
        this.title = title;
    }
}
