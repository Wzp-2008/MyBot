package cn.wzpmc.user.group;

import lombok.Data;

/**
 * 匿名成员消息
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午8:57
 */
@Data
public class GroupUserAnonymousInfo {
    /**
     * 匿名ID
     *
     * @since 2024/8/1 下午8:58 v0.0.2-dev
     */
    private Long id;
    /**
     * 匿名名称
     *
     * @since 2024/8/1 下午8:58 v0.0.2-dev
     */
    private String name;
    /**
     * 匿名成员flag
     *
     * @since 2024/8/1 下午8:58 v0.0.2-dev
     */
    private String flag;
}
