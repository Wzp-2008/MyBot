package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * "@"某人
 * @author MoYiJiangNan
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:22
 */
@Data
@NoArgsConstructor
public class At implements JsonMessagePart {
    /**
     * "@"的 QQ 号，all 表示全体成员
     * @since 2024/8/2 下午11:50 v0.0.3-dev
     */
    private String qq;

    @Override
    public PartType getPartType() {
        return PartType.AT;
    }

    public JSONObject getData() {
        return JSONObject.of("qq", this.qq);
    }
}
