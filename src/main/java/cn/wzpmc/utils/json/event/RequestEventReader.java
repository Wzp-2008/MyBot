package cn.wzpmc.utils.json.event;

import cn.wzpmc.events.request.RequestEvent;
import cn.wzpmc.events.request.RequestEventType;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;

/**
 * 请求事件反序列化
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午8:56
 */
public class RequestEventReader implements ObjectReader<RequestEvent> {
    @Override
    public RequestEvent readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
        JSONReader.SavePoint mark = jsonReader.mark();
        JSONObject jsonObject = jsonReader.readJSONObject();
        RequestEventType requestType = RequestEventType.valueOf(jsonObject.getString("request_type").toUpperCase());
        jsonReader.reset(mark);
        return jsonReader.read(requestType.clazz);
    }
}
