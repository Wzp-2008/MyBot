package cn.wzpmc.utils.json.event;

import cn.wzpmc.events.meta.MetaEvent;
import cn.wzpmc.events.meta.MetaEventType;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;

/**
 * 元事件反序列化
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午8:56
 */
public class MetaEventReader implements ObjectReader<MetaEvent> {
    @Override
    public MetaEvent readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
        JSONReader.SavePoint mark = jsonReader.mark();
        JSONObject jsonObject = jsonReader.readJSONObject();
        MetaEventType metaEventType = MetaEventType.valueOf(jsonObject.getString("meta_event_type").toUpperCase());
        jsonReader.reset(mark);
        return jsonReader.read(metaEventType.clazz);
    }
}
