package cn.wzpmc.utils.json.message;

import cn.wzpmc.api.message.json.JsonMessage;
import cn.wzpmc.api.message.json.JsonMessagePart;
import cn.wzpmc.api.message.json.parts.PartType;
import cn.wzpmc.api.message.json.parts.music.MusicType;
import cn.wzpmc.api.message.json.parts.node.CustomNode;
import cn.wzpmc.api.message.json.parts.node.SingleNode;
import cn.wzpmc.api.message.json.parts.poke.Poke;
import cn.wzpmc.api.message.json.parts.poke.PokeType;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.reader.ObjectReader;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

/**
 * json消息解析器
 * @author wzp
 * @since 2024/8/3 下午6:25
 * @version 0.0.3-dev
 */
@Log4j2
public class JsonMessageReader implements ObjectReader<JsonMessage> {
    @SneakyThrows
    @Override
    public JsonMessage readObject(JSONReader jsonReader, Type fieldType, Object fieldName, long features) {
        JSONArray objects = jsonReader.readJSONArray();
        JsonMessage message = new JsonMessage();
        List<JsonMessagePart> messageParts = message.getMessageParts();
        messageFor: for (int i = 0; i < objects.size(); i++) {
            JSONObject jsonObject = objects.getJSONObject(i);
            PartType type = jsonObject.getObject("type", PartType.class);
            Class<? extends JsonMessagePart> clazz = type.clazz;
            JSONObject dataObject = jsonObject.getJSONObject("data");
            if (type.equals(PartType.POKE)){
                Integer pokeType = dataObject.getInteger("type");
                Integer pokeId = dataObject.getInteger("id");
                for (PokeType value : PokeType.values()) {
                    if (value.type.equals(pokeType) && value.id.equals(pokeId)){
                        messageParts.add(new Poke(value));
                        continue messageFor;
                    }
                }
                log.warn("无法识别的戳一戳类型：type: {}, id: {}", pokeType, pokeId);
                messageParts.add(new Poke());
            }
            if (type.equals(PartType.NODE)){
                if (jsonObject.containsKey("content")){
                    clazz = CustomNode.class;
                }else{
                    clazz = SingleNode.class;
                }
            }
            if (type.equals(PartType.MUSIC)){
                MusicType musicType = dataObject.getObject("type", MusicType.class);
                dataObject.put("musicType", musicType);
                clazz = musicType.clazz;
            }
            Constructor<? extends JsonMessagePart> declaredConstructor = clazz.getDeclaredConstructor();
            JsonMessagePart resultPart = declaredConstructor.newInstance();
            for (Field declaredField : clazz.getDeclaredFields()) {
                JSONField annotation = declaredField.getAnnotation(JSONField.class);
                String name = Objects.isNull(annotation) ? declaredField.getName() : annotation.name();
                if (!dataObject.containsKey(name)) {
                    continue;
                }
                declaredField.setAccessible(true);
                Class<?> thisFieldClass = declaredField.getType();
                Object value = dataObject.getObject(name, thisFieldClass);
                declaredField.set(resultPart, value);
            }
            messageParts.add(resultPart);
        }
        return message;
    }
}
