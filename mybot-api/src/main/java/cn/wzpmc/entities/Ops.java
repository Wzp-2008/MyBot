package cn.wzpmc.entities;

import lombok.Getter;

import java.util.*;

/**
 * 分群op列表
 *
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 20:16
 */
@Getter
public final class Ops {
    /**
     * BOT总管理员
     *
     * @since 2024/8/25 20:17 v1.0.0
     */
    private final Set<Long> admins = new HashSet<>();
    /**
     * 群内管理员
     *
     * @since 2024/8/25 20:17 v1.0.0
     */
    private final Map<String, List<Long>> groupAdmins = new HashMap<>();

    /**
     * 判断此用户在群中是否为管理
     *
     * @param groupId 群ID
     * @param id      用户ID
     * @return 是否管理
     * @author wzp
     * @since 2024/8/25 20:20 v1.0.0
     */
    public boolean isAdmin(Long groupId, Long id) {
        if (admins.contains(id)) {
            return true;
        }
        List<Long> longs = groupAdmins.get(groupId.toString());
        if (longs == null) {
            return false;
        }
        return longs.contains(id);
    }

    /**
     * 判断此用户是否为管理
     *
     * @param id 用户ID
     * @return 是否管理
     * @author wzp
     * @since 2024/8/25 20:20 v1.0.0
     */
    public boolean isAdmin(Long id) {
        return admins.contains(id);
    }
}
