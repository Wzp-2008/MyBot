package cn.wzpmc.message.json.parts;

import cn.wzpmc.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * JSON 消息
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:43
 */
@Data
@NoArgsConstructor
public class CustomJSONMessage implements JsonMessagePart {
    /**
     * JSON 内容
     *
     * @since 2024/8/3 下午6:11 v0.0.3-dev
     */
    private JSONObject data;

    @Override
    public PartType getPartType() {
        return PartType.JSON;
    }

    @Override
    public JSONObject getData() {
        return JSONObject.of("data", this.data);
    }
}
