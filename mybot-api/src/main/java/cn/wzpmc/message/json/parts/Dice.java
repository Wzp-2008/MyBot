package cn.wzpmc.message.json.parts;

import cn.wzpmc.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 掷骰子魔法表情
 *
 * @author MoYiJiangNan
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:26
 */
@Data
@NoArgsConstructor
public class Dice implements JsonMessagePart {
    public PartType getPartType() {
        return PartType.DICE;
    }

    public JSONObject getData() {
        return JSONObject.of();
    }
}
