package cn.wzpmc.utils.json.event;

import cn.wzpmc.events.message.MessageEvent;
import cn.wzpmc.events.message.MessageType;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;

/**
 * 消息事件反序列化
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午8:56
 */
public class MessageEventReader implements ObjectReader<MessageEvent<?, ?>> {
    @Override
    public MessageEvent<?, ?> readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
        JSONReader.SavePoint mark = jsonReader.mark();
        JSONObject jsonObject = jsonReader.readJSONObject();
        MessageType messageType = MessageType.valueOf(jsonObject.getString("message_type").toUpperCase());
        jsonReader.reset(mark);
        return jsonReader.read(messageType.clazz);
    }
}
