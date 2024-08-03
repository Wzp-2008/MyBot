package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * QQ表情
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:00
 */
@Data
@NoArgsConstructor
public class Face implements JsonMessagePart {
    /**
     * <a href="https://github.com/richardchien/coolq-http-api/wiki/%E8%A1%A8%E6%83%85-CQ-%E7%A0%81-ID-%E8%A1%A8">qq表情ID</a>
     * @since 2024/8/2 下午11:46 v0.0.3-dev
     */
    private Short id;
    @Override
    public PartType getPartType() {
        return PartType.FACE;
    }

    @Override
    public JSONObject getData() {
        return JSONObject.of("face", id);
    }

}
