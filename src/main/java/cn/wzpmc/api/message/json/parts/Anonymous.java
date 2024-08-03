package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 匿名发消息
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:19
 */
@Data
@NoArgsConstructor
public class Anonymous implements JsonMessagePart {
    /**
     * 可选，表示无法匿名时是否继续发送
     * @since 2024/8/3 下午5:55 v0.0.3-dev
     */
    private boolean ignore = false;
    @Override
    public PartType getPartType() {
        return PartType.ANONYMOUS;
    }

    @Override
    public JSONObject getData() {
        return JSONObject.of("ignore", this.ignore);
    }
}
