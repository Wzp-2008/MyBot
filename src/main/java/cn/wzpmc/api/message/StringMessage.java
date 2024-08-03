package cn.wzpmc.api.message;

import cn.wzpmc.api.message.json.JsonMessagePart;
import cn.wzpmc.api.message.json.parts.PartType;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 纯文本消息
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StringMessage implements MessageComponent, JsonMessagePart {
    @JSONField(name = "text")
    private String message;
    @Override
    public String toMessageString() {
        return this.message;
    }

    /**
     * 构建纯文本消息
     * @author wzp
     * @since 2024/7/31 上午2:41 v0.0.1-dev
     * @param message 消息文本
     * @return 文本消息对象
     */
    public static StringMessage text(String message){
        return new StringMessage(message);
    }

    @Override
    public PartType getPartType() {
        return PartType.TEXT;
    }

    @Override
    public JSONObject getData() {
        return JSONObject.of("text", this.message);
    }

    @Override
    public String getTextDisplay() {
        return this.message;
    }

    @Override
    public String getCQCode() {
        return this.message;
    }
}
