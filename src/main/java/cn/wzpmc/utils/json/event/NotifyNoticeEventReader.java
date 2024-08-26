package cn.wzpmc.utils.json.event;

import cn.wzpmc.events.notice.notify.NotifyEvent;
import cn.wzpmc.events.notice.notify.NotifySubType;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;

/**
 * 群提醒事件反序列化
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午8:53
 */
public class NotifyNoticeEventReader implements ObjectReader<NotifyEvent> {
    @Override
    public NotifyEvent readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
        JSONReader.SavePoint mark = jsonReader.mark();
        JSONObject jsonObject = jsonReader.readJSONObject();
        NotifySubType subType = NotifySubType.valueOf(jsonObject.getString("sub_type").toUpperCase());
        jsonReader.reset(mark);
        return jsonReader.read(subType.clazz);
    }
}
