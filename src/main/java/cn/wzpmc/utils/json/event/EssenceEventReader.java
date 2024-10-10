package cn.wzpmc.utils.json.event;

import cn.wzpmc.events.notice.essence.EssenceEvent;
import cn.wzpmc.events.notice.essence.EssenceSubType;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;

/**
 * @author wzp
 * @version 1.0.3
 * @since 2024/9/16 21:36
 */
public class EssenceEventReader implements ObjectReader<EssenceEvent> {
    @Override
    public EssenceEvent readObject(JSONReader jsonReader, Type type, Object o, long l) {
        JSONReader.SavePoint mark = jsonReader.mark();
        JSONObject jsonObject = jsonReader.readJSONObject();
        EssenceSubType postType = EssenceSubType.valueOf(jsonObject.getString("sub_type").toUpperCase());
        jsonReader.reset(mark);
        EssenceEvent event = jsonReader.read(postType.clazz);
        jsonReader.close();
        return event;
    }
}
