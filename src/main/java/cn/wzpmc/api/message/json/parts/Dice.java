package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MoYiJiangNan
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:26
 */
@Data
@NoArgsConstructor
public class Dice implements JsonMessagePart {
    public PartType getPartType(){
        return PartType.DICE;
    }
    public JSONObject getData(){
        return JSONObject.of();
    }
}