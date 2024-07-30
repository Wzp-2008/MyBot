package cn.wzpmc.api.message.json;

import cn.wzpmc.api.message.MessageComponent;
import com.alibaba.fastjson2.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JSON消息
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:34
 */
public class JsonMessage implements MessageComponent {
    List<JsonMessagePart> messageParts = new ArrayList<>();
    @Override
    public String toMessageString() {
        return JSON.toJSONString(messageParts);
    }
    public String toTextDisplay() {
        return this.messageParts.stream().map(JsonMessagePart::getTextDisplay).collect(Collectors.joining(""));
    }
}
