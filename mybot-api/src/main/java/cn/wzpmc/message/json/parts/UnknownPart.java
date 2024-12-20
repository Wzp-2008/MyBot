package cn.wzpmc.message.json.parts;

import cn.wzpmc.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 未知消息类型
 *
 * @author wzp
 * @version 1.0.5
 * @since 2024/11/17 17:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnknownPart implements JsonMessagePart {
    /**
     * 类型文本
     *
     * @since 2024/11/17 17:32 v1.0.5
     */
    private String type;
    /**
     * 消息数据
     *
     * @since 2024/11/17 17:33 v1.0.5
     */
    private JSONObject data;

    @Override
    public PartType getPartType() {
        return PartType.UNKNOWN;
    }

    @Override
    public String getStringPartType() {
        return type;
    }

    @Override
    public JSONObject getData() {
        return data;
    }
}
