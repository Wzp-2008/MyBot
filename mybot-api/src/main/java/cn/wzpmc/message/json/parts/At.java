package cn.wzpmc.message.json.parts;

import cn.wzpmc.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * "@"某人
 *
 * @author MoYiJiangNan
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:22
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class At implements JsonMessagePart {
    /**
     * "@"的 QQ 号，all 表示全体成员
     *
     * @since 2024/8/2 下午11:50 v0.0.3-dev
     */
    private String qq;

    /**
     * 获取用于@全体成员的AT对象
     *
     * @return @全体成员的At对象
     * @author wzp
     * @since 2024/8/31 23:51 v1.0.3
     */
    public static At all() {
        return new At("all");
    }

    /**
     * 获取用于@用户的AT对象
     *
     * @param userId 用户ID
     * @return @指定用户的At对象
     * @author wzp
     * @since 2024/8/31 23:50 v1.0.3
     */
    public static At user(Long userId) {
        return new At(userId.toString());
    }

    @Override
    public PartType getPartType() {
        return PartType.AT;
    }

    public JSONObject getData() {
        return JSONObject.of("qq", this.qq);
    }
}
