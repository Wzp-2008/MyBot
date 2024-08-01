package cn.wzpmc.api.user.group;

import cn.wzpmc.api.message.MessageComponent;
import cn.wzpmc.api.user.IUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 群内用户
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
     * @since 2024/8/1 下午8:54 v0.0.2-dev
     */
    private String card;
    /**
     * 地区
     * @since 2024/8/1 下午8:54 v0.0.2-dev
     */
    private String area;
    /**
     * 成员等级
     * @since 2024/8/1 下午8:53 v0.0.2-dev
     */
    private String level;
    /**
     * 群角色
     * @since 2024/8/1 下午8:53 v0.0.2-dev
     */
    private GroupUserRole role;
    /**
     * 专属头衔
     * @since 2024/8/1 下午8:53 v0.0.2-dev
     */
    private String title;
    @Override
    public void sendMessage(MessageComponent messageComponent) {

    }
}
