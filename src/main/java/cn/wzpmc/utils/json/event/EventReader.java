package cn.wzpmc.utils.json.event;

import cn.wzpmc.events.Event;
import cn.wzpmc.events.EventPostType;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;

/**
 * event类型JSON反序列化
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午2:07
 */
public class EventReader implements ObjectReader<Event> {
    @Override
    public Event readObject(JSONReader jsonReader, Type type, Object o, long l) {
        JSONReader.SavePoint mark = jsonReader.mark();
        JSONObject jsonObject = jsonReader.readJSONObject();
        EventPostType postType = EventPostType.valueOf(jsonObject.getString("post_type").toUpperCase());
        jsonReader.reset(mark);
        Event event = jsonReader.read(postType.clazz);
        jsonReader.close();
        return event;
    }
}
