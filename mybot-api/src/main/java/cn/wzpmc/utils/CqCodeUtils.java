package cn.wzpmc.utils;

import cn.wzpmc.message.json.JsonMessagePart;
import cn.wzpmc.message.json.parts.PartType;
import cn.wzpmc.message.json.parts.music.MusicType;
import cn.wzpmc.message.json.parts.node.CustomNode;
import cn.wzpmc.message.json.parts.node.SingleNode;
import cn.wzpmc.message.json.parts.poke.Poke;
import cn.wzpmc.message.json.parts.poke.PokeType;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * CQ码相关工具类
 *
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/26 14:06
 */
@Log4j2
public class CqCodeUtils {
    /**
     * 判断一个字符串是否为CQ码
     *
     * @param word 一个词（不包含空格）
     * @return 是否为CQ码
     * @author wzp
     * @since 2024/8/26 14:08 v1.0.0
     */
    public static boolean isCQ(String word) {
        return word.startsWith("[") && word.endsWith("]");
    }

    /**
     * 解析CQ码为Map
     *
     * @param word 一个词（不包含空格）
     * @return 解析后的Map
     * @author wzp
     * @since 2024/8/26 14:08 v1.0.0
     */
    public static Map<String, String> parse(String word) {
        StringBuilder key = new StringBuilder();
        boolean keyDone = false;
        StringBuilder value = new StringBuilder();
        Map<String, String> result = new HashMap<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '[') continue;
            if (c == ']') {
                result.put(key.toString(), value.toString());
            }
            if (keyDone) {
                if (c == ',') {
                    result.put(key.toString(), value.toString());
                    key = new StringBuilder();
                    value = new StringBuilder();
                    keyDone = false;
                    continue;
                }
                value.append(c);
                continue;
            }
            if (c == ':' || c == '=') {
                keyDone = true;
                continue;
            }
            key.append(c);
        }
        return result;
    }

    /**
     * 将文本转化为JsonMessagePart
     *
     * @param word 一个词（不包含空格）
     * @return 消息文本段
     * @author wzp
     * @since 2024/8/26 14:34 v1.0.0
     */
    @SneakyThrows
    public static JsonMessagePart parseToPart(String word) {
        Map<String, String> parse = parse(word);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(parse));
        Object cq = jsonObject.get("CQ");
        JSONObject jsonObj = JSONObject.of("type", cq, "data", jsonObject);
        return parsePart(jsonObj);
    }

    /**
     * 将json解析为单个消息段
     *
     * @param jsonObject json数据
     * @return 消息段
     * @throws InvocationTargetException 调用构造方法失败时抛出
     * @throws InstantiationException    实例化错误时抛出
     * @throws IllegalAccessException    实例化错误时抛出
     * @throws NoSuchMethodException     找不到构造方法时抛出
     * @author wzp
     * @since 2024/8/26 14:40 v1.0.0
     */
    public static JsonMessagePart parsePart(JSONObject jsonObject) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        PartType type = jsonObject.getObject("type", PartType.class);
        Class<? extends JsonMessagePart> clazz = type.clazz;
        JSONObject dataObject = jsonObject.getJSONObject("data");
        if (type.equals(PartType.POKE)) {
            Integer pokeType = dataObject.getInteger("type");
            Integer pokeId = dataObject.getInteger("id");
            for (PokeType value : PokeType.values()) {
                if (value.type.equals(pokeType) && value.id.equals(pokeId)) {
                    return new Poke(value);
                }
            }
            log.warn("无法识别的戳一戳类型：type: {}, id: {}", pokeType, pokeId);
            return new Poke();
        }
        if (type.equals(PartType.NODE)) {
            if (jsonObject.containsKey("content")) {
                clazz = CustomNode.class;
            } else {
                clazz = SingleNode.class;
            }
        }
        if (type.equals(PartType.MUSIC)) {
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
        return resultPart;
    }
}
