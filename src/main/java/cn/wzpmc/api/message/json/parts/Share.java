package cn.wzpmc.api.message.json.parts;

import cn.wzpmc.api.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

/**
 * 链接分享
 * @author MoYiJiangNan
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:30
 */
@Data
@NoArgsConstructor
public class Share implements JsonMessagePart {
    /**
     * URL
     * @since 2024/8/3 下午5:56 v0.0.3-dev
     */
    private URL url;
    /**
     * 标题
     * @since 2024/8/3 下午5:56 v0.0.3-dev
     */
    private String title;
    /**
     * 发送时可选，内容描述
     * @since 2024/8/3 下午5:56 v0.0.3-dev
     */
    private String content;
    /**
     * 发送时可选，图片 URL
     * @since 2024/8/3 下午5:56 v0.0.3-dev
     */
    private URL image;

    @Override
    public PartType getPartType() {
        return PartType.SHARE;
    }

    public JSONObject getData() {
        return new JSONObject()
                .fluentPut("url", this.url)
                .fluentPut("title", this.title)
                .fluentPut("content", this.content)
                .fluentPut("image", this.image);
    }
}
