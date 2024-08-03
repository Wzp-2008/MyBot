package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 窗口抖动（戳一戳）
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:18
 */
@Data
@NoArgsConstructor
public class Shake implements JsonMessagePart {

    @Override
    public PartType getPartType() {
        return PartType.SHAKE;
    }

    @Override
    public JSONObject getData() {
        return JSONObject.of();
    }
}
