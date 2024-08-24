package cn.wzpmc.api.api.actions.message.set;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 设置群组专属头衔
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:08
 */
public class SetGroupSpecialTitleAction extends Action<SetGroupSpecialTitleAction.Params, Void> {
    /**
     * 设置群组专属头衔
     * @author wzp
     * @since 2024/8/24 23:58 v0.0.6-dev
     * @param groupId 群号
     * @param userId 要设置的 QQ 号
     * @param specialTitle 专属头衔，不填或空字符串表示删除专属头衔
     * @param duration 专属头衔有效期，单位秒，-1 表示永久，不过此项似乎没有效果，可能是只有某些特殊的时间长度有效，有待测试
     */
    public SetGroupSpecialTitleAction(Long groupId, Long userId, String specialTitle, Long duration){
        super.setAction(Actions.SET_GROUP_SPECIAL_TITLE);
        super.setParams(new Params(groupId, userId, specialTitle, duration));
    }

    /**
     * 设置群组专属头衔
     * @author wzp
     * @since 2024/8/24 23:58 v0.0.6-dev
     * @param groupId 群号
     * @param userId 要设置的 QQ 号
     * @param specialTitle 专属头衔，不填或空字符串表示删除专属头衔
     */
    public SetGroupSpecialTitleAction(Long groupId, Long userId, String specialTitle){
        this(groupId, userId, specialTitle, -1L);
    }

    /**
     * 设置群组专属头衔
     * @author wzp
     * @since 2024/8/24 23:58 v0.0.6-dev
     * @param groupId 群号
     * @param userId 要设置的 QQ 号
     */
    public SetGroupSpecialTitleAction(Long groupId, Long userId){
        this(groupId, userId, "");
    }

    /**
     * 设置群组专属头衔
     * @author wzp
     * @since 2024/8/24 23:58 v0.0.6-dev
     * @param groupId 群号
     * @param userId 要设置的 QQ 号
     * @param duration 专属头衔有效期，单位秒，-1 表示永久，不过此项似乎没有效果，可能是只有某些特殊的时间长度有效，有待测试
     */
    public SetGroupSpecialTitleAction(Long groupId, Long userId, Long duration){
        this(groupId, userId, "", duration);
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 群号
         * @since 2024/8/24 21:04 v0.0.6-dev
         */
        @JSONField(name = "group_id")
        private Long groupId;
        /**
         * 要设置的 QQ 号
         * @since 2024/8/24 21:04 v0.0.6-dev
         */
        @JSONField(name = "user_id")
        private Long userId;
        /**
         * 专属头衔，不填或空字符串表示删除专属头衔
         * @since 2024/8/24 21:04 v0.0.6-dev
         */
        @JSONField(name = "special_title")
        private String specialTitle;
        /**
         * 专属头衔有效期，单位秒，-1 表示永久，不过此项似乎没有效果，可能是只有某些特殊的时间长度有效，有待测试
         * @since 2024/8/24 21:04 v0.0.6-dev
         */
        @JSONField(defaultValue = "-1")
        private Long duration;
    }
}
