package cn.wzpmc.utils.json.message;

import cn.wzpmc.message.StringMessage;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;

import java.lang.reflect.Type;

/**
 * @author wzp
 * @version 1.0.3
 * @since 2024/8/31 23:20
 */
public class StringMessageWriter implements ObjectWriter<StringMessage> {
    @Override
    public void write(JSONWriter jsonWriter, Object o, Object o1, Type type, long l) {
        if (o instanceof StringMessage) {
            jsonWriter.writeString(((StringMessage) o).getMessage());
        }
    }
}
