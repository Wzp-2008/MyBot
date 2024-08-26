package cn.wzpmc.utils.json.message;

import cn.wzpmc.message.StringMessage;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;

/**
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/3 下午8:38
 */
public class StringMessageReader implements ObjectReader<StringMessage> {
    @Override
    public StringMessage readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
        if (jsonReader.isObject()) {
            JSONObject jsonObject = jsonReader.readJSONObject();
            return new StringMessage(jsonObject.getJSONObject("data").getString("text"));
        }
        String s = jsonReader.readString();
        return new StringMessage(s);
    }
}
