package cn.wzpmc.message.json;

import cn.wzpmc.message.MessageComponent;
import com.alibaba.fastjson2.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JSON消息
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:34
 */
@Data
public class JsonMessage implements MessageComponent {
    private final List<JsonMessagePart> messageParts = new ArrayList<>();

    /**
     * 将消息转为JSON文本
     *
     * @return json文本
     * @author wzp
     * @since 2024/8/23 21:36 v0.0.5-dev
     */
    @Override
    public String toMessageString() {
        return JSON.toJSONString(messageParts);
    }

    /**
     * 将JSON消息转化为纯文本显示方式
     *
     * @return 文本
     * @author wzp
     * @since 2024/8/23 21:35 v0.0.5-dev
     */
    public String toTextDisplay() {
        return this.messageParts.stream().map(JsonMessagePart::getTextDisplay).collect(Collectors.joining(""));
    }

    /**
     * 将JSON消息转换为CQ文本消息
     *
     * @return 文本消息
     * @author wzp
     * @since 2024/8/2 下午9:36 v0.0.3-dev
     */
    public String toCqString() {
        StringBuilder stringBuilder = new StringBuilder();
        this.messageParts.stream().map(JsonMessagePart::getCQCode).forEach(stringBuilder::append);
        return stringBuilder.toString();
    }
}
