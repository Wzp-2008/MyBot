package cn.wzpmc.entities;

import cn.wzpmc.user.IUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 简略好友信息
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 19:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class FriendInformation extends IUser {
    /**
     * 添加后的好友备注（仅在同意时有效）
     *
     * @since 2024/8/24 23:59 v0.0.6-dev
     */
    private String remark;
}
