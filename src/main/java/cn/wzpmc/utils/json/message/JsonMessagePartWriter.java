package cn.wzpmc.utils.json.message;

import cn.wzpmc.message.json.JsonMessagePart;
import cn.wzpmc.utils.TextUtils;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Type;

/**
 * @author wzp
 * @since 2025/2/1 04:19
 * @version 1.0.5
 **/
@Log4j2
public class JsonMessagePartWriter implements ObjectWriter<JsonMessagePart> {
    @Override
    public void write(JSONWriter jsonWriter, Object object, Object fieldName, Type fieldType, long features) {
        if (object instanceof JsonMessagePart) {
            log.debug("raw json message part content: {}", TextUtils.simplifyString(object.toString()));
            JSONObject from = JSONObject.from(object);
            from.put("type", ((JsonMessagePart) object).getStringPartType());
            jsonWriter.write(from);
        }
    }
}
