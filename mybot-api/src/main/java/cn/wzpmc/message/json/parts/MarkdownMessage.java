package cn.wzpmc.message.json.parts;

import cn.wzpmc.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Markdown格式消息
 *
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 15:19
 */
@Data
@NoArgsConstructor
public class MarkdownMessage implements JsonMessagePart {
    /**
     * Markdown格式数据
     *
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
