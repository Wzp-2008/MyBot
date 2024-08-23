package cn.wzpmc.utils.json.user;

import cn.wzpmc.api.user.Friend;
import cn.wzpmc.api.user.IUser;
import cn.wzpmc.api.user.group.GroupUser;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;

/**
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/17 23:12
 */
public class IUserReader implements ObjectReader<IUser> {
    @Override
    public IUser readObject(JSONReader jsonReader, Type type, Object o, long l) {
        JSONObject jsonObject = jsonReader.readJSONObject();
        if (jsonObject.containsKey("role")) {
            return jsonObject.to(GroupUser.class);
        }
        return jsonObject.to(Friend.class);
    }
}
