package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 合并转发
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:38
 */
@Data
@NoArgsConstructor
public class Forward implements JsonMessagePart {
    /**
     * 合并转发 ID，需通过 get_forward_msg API 获取具体内容
     * @since 2024/8/3 下午6:10 v0.0.3-dev
     */
    private Long id;
    @Override
    public PartType getPartType() {
        return PartType.FORWARD;
    }

    @Override
    public JSONObject getData() {
        return JSONObject.of("id", this.id);
    }
}
