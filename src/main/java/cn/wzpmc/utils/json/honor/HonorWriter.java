package cn.wzpmc.utils.json.honor;

import cn.wzpmc.entities.HonorType;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.writer.ObjectWriter;

import java.lang.reflect.Type;

/**
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 23:23
 */
public class HonorWriter implements ObjectWriter<HonorType> {
    @Override
    public void write(JSONWriter jsonWriter, Object o, Object o1, Type type, long l) {
        jsonWriter.writeString(((HonorType) o).name());
    }
}
