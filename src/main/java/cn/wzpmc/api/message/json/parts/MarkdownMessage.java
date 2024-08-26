package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Markdown格式消息
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 15:19
 */
@Data
@AllArgsConstructor
public class MarkdownMessage implements JsonMessagePart {
    /**
     * Markdown格式数据
     * @since 2024/8/25 15:20 v1.0.0
     */
    private String data;
    @Override
    public PartType getPartType() {
        return PartType.MARKDOWN;
    }

    @Override
    public JSONObject getData() {
        return JSONObject.of("data", data);
    }
}
