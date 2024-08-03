package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 位置
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:22
 */
@Data
@NoArgsConstructor
public class Location implements JsonMessagePart {
    /**
     * 纬度
     * @since 2024/8/3 下午6:06 v0.0.3-dev
     */
    private Double lat;
    /**
     * 经度
     * @since 2024/8/3 下午6:07 v0.0.3-dev
     */
    private Double lon;
    /**
     * 发送时可选，标题
     * @since 2024/8/3 下午6:07 v0.0.3-dev
     */
    private String title = "";
    /**
     * 发送时可选，内容描述
     * @since 2024/8/3 下午6:07 v0.0.3-dev
     */
    private String content = "";
    @Override
    public PartType getPartType() {
        return PartType.LOCATION;
    }

    @Override
    public JSONObject getData() {
        return new JSONObject()
                .fluentPut("lat", this.lat)
                .fluentPut("lon", this.lon)
                .fluentPut("title", this.title)
                .fluentPut("content", this.content);
    }
}
