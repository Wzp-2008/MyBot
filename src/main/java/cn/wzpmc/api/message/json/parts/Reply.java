package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 回复
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:33
 */
@Data
@NoArgsConstructor
public class Reply implements JsonMessagePart {
    /**
     * 回复时引用的消息 ID
     * @since 2024/8/3 下午6:08 v0.0.3-dev
     */
    private Long id;
    @Override
    public PartType getPartType() {
        return PartType.REPLY;
    }

    @Override
    public JSONObject getData() {
        return JSONObject.of("id", id);
    }
}
