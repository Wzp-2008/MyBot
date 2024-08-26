package cn.wzpmc.utils.json.message;

import cn.wzpmc.message.json.JsonMessage;
import cn.wzpmc.message.json.JsonMessagePart;
import cn.wzpmc.utils.CqCodeUtils;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Type;
import java.util.List;

/**
 * json消息解析器
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/3 下午6:25
 */
@Log4j2
public class JsonMessageReader implements ObjectReader<JsonMessage> {
    @SneakyThrows
    @Override
    public JsonMessage readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
        JSONArray objects = jsonReader.readJSONArray();
        JsonMessage message = new JsonMessage();
        List<JsonMessagePart> messageParts = message.getMessageParts();
        for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject = objects.getJSONObject(i);
            messageParts.add(CqCodeUtils.parsePart(jsonObject));
        }
        return message;
    }
}
