package cn.wzpmc.utils.json.action;

import cn.wzpmc.api.Actions;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;

import java.lang.reflect.Type;

/**
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/17 22:05
 */
public class ActionWriter implements ObjectWriter<Actions> {
    @Override
    public void write(JSONWriter jsonWriter, Object o, Object fieldName, Type type, long l) {
        Actions actions = (Actions) o;
        jsonWriter.writeString(actions.name().toLowerCase());
    }
}
