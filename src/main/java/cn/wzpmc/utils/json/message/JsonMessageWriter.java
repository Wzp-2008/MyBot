package cn.wzpmc.utils.json.message;

import cn.wzpmc.message.json.JsonMessage;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;

import java.lang.reflect.Type;

/**
 * JSON格式消息反序列化
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/3 下午6:28
 */
public class JsonMessageWriter implements ObjectWriter<JsonMessage> {
    @Override
    public void write(JSONWriter jsonWriter, Object object, Object fieldName, Type fieldType, long features) {
        if (object instanceof JsonMessage) {
            jsonWriter.write(((JsonMessage) object).getMessageParts());
        }
    }
}
