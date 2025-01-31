package cn.wzpmc.utils.json.message;

import cn.wzpmc.message.StringMessage;
import cn.wzpmc.message.json.JsonMessage;
import cn.wzpmc.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONFactory;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import com.alibaba.fastjson2.writer.ObjectWriterCreator;
import com.alibaba.fastjson2.writer.ObjectWriterProvider;

import java.lang.reflect.Type;
import java.util.List;

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
            List<JsonMessagePart> messageParts = ((JsonMessage) object).getMessageParts();
            jsonWriter.startArray();
            int size = messageParts.size();
            int i = 0;
            for (JsonMessagePart messagePart : messageParts) {
                if (messagePart instanceof StringMessage) {
                    ObjectWriterProvider defaultObjectReaderProvider = JSONFactory.getDefaultObjectWriterProvider();
                    ObjectWriterCreator creator = defaultObjectReaderProvider.getCreator();
                    ObjectWriter<?> objectWriter = creator.createObjectWriter(StringMessage.class);
                    objectWriter.write(jsonWriter, messagePart);
                } else {
                    new JsonMessagePartWriter().write(jsonWriter, messagePart);
                }
                if (i != size - 1) {
                    jsonWriter.writeComma();
                }
                i++;
            }
            jsonWriter.endArray();
        }
    }
}
