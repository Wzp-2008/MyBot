package cn.wzpmc.utils.json.action;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.ActionResponse;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.entities.api.ApiResponseRequired;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.alibaba.fastjson2.reader.ObjectReader;

import java.lang.reflect.Type;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/16 21:50
 */
public class ActionReader implements ObjectReader<ActionResponse<?>> {
    public static final ConcurrentHashMap<UUID, ApiResponseRequired<?, ?>> tasks = new ConcurrentHashMap<>();
    @Override
    public ActionResponse<?> readObject(JSONReader jsonReader, Type type, Object o, long l) {
        JSONObject jsonObject = jsonReader.readJSONObject();
        UUID echo = jsonObject.getObject("echo", UUID.class);
        ApiResponseRequired<?, ?> apiResponseRequired = tasks.get(echo);
        Action<?, ?> request = apiResponseRequired.getRequest();
        Actions action = request.getAction();
        String status = jsonObject.getString("status");
        short retcode = jsonObject.getShort("retcode");
        Object dataObj = null;
        if (action.array){
            JSONArray data = jsonObject.getJSONArray("data");
            dataObj = data.toJavaList(action.responseClass);
        } else {
            JSONObject data = jsonObject.getJSONObject("data");
            if (data != null){
                dataObj = data.to(action.responseClass);
            }
        }
        return new ActionResponse<>(status, retcode, dataObj, echo);
    }
}
