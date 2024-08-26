package cn.wzpmc.entities;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 群成员荣耀信息
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 19:39
 */
@Data
@AllArgsConstructor
public class GroupHonorUser {
    /**
     * QQ 号
     *
     * @since 2024/8/25 00:00 v0.0.6-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
    /**
     * 昵称
     *
     * @since 2024/8/25 00:00 v0.0.6-dev
     */
    private String nickname;
    /**
     * 头像 URL
     *
     * @since 2024/8/25 00:00 v0.0.6-dev
     */
    private String avatar;
    /**
     * 荣誉描述
     *
     * @since 2024/8/25 00:00 v0.0.6-dev
     */
    private String description;
}
