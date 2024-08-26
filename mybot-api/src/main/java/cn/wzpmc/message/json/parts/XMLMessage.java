package cn.wzpmc.message.json.parts;

import cn.wzpmc.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * XML消息
 *
 * @author MoYiJiangNan
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:40
 */
@Data
@NoArgsConstructor
public class XMLMessage implements JsonMessagePart {
    /**
     * XML 内容
     *
     * @since 2024/8/3 下午6:11 v0.0.3-dev
     */
    private String data;

    @Override
    public PartType getPartType() {
        return PartType.XML;
    }

    public JSONObject getData() {
        return new JSONObject()
                .fluentPut("data", data);
    }
}
