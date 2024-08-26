package cn.wzpmc.utils.json.user;

import cn.wzpmc.user.IBot;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 21:27
 */
public class IBotReader implements ObjectReader<IBot> {
    @Override
    public IBot createInstance(Map map, long features) {
        Long userId = Long.parseLong(map.get("user_id").toString());
        String nickname = (String) map.get("nickname");
        IBot instance = IBot.getInstance();
        if (instance.getNickname() == null && instance.getId() == null) {
            instance.setId(userId);
            instance.setName(nickname);
        }
        return instance;
    }

    @Override
    public IBot readObject(JSONReader jsonReader, Type type, Object o, long l) {
        JSONObject jsonObject = jsonReader.readJSONObject();
        IBot instance = IBot.getInstance();
        if (instance.getNickname() == null && instance.getId() == null) {
            instance.setId(jsonObject.getLong("user_id"));
            instance.setName(jsonObject.getString("nickname"));
        }
        return instance;
    }
}
