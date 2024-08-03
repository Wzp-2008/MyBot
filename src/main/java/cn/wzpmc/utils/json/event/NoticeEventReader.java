package cn.wzpmc.utils.json.event;

import cn.wzpmc.api.events.notice.NoticeEvent;
import cn.wzpmc.api.events.notice.NoticeType;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;

/**
 * 提醒类型事件反序列化
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午8:48
 */
public class NoticeEventReader implements ObjectReader<NoticeEvent> {
    @Override
    public NoticeEvent readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
        JSONReader.SavePoint mark = jsonReader.mark();
        JSONObject jsonObject = jsonReader.readJSONObject();
        NoticeType noticeType = NoticeType.valueOf(jsonObject.getString("notice_type").toUpperCase());
        jsonReader.reset(mark);
        return jsonReader.read(noticeType.clazz);
    }
}
