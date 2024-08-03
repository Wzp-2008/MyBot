package cn.wzpmc.api.message.json.parts.music;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 音乐自定义分享
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MusicCustomShare extends Music {
    /**
     * 点击后跳转目标 URL
     * @since 2024/8/3 下午6:08 v0.0.3-dev
     */
    private String url;
    /**
     * 音乐 URL
     * @since 2024/8/3 下午6:08 v0.0.3-dev
     */
    private String audio;
    /**
     * 标题
     * @since 2024/8/3 下午6:08 v0.0.3-dev
     */
    private String title;
    /**
     * 发送时可选，内容描述
     * @since 2024/8/3 下午6:08 v0.0.3-dev
     */
    private String content = "";
    /**
     * 发送时可选，图片 URL
     * @since 2024/8/3 下午6:08 v0.0.3-dev
     */
    private String image = "";
    @Override
    public JSONObject getData() {
        return super.getData()
                .fluentPut("url", this.url)
                .fluentPut("audio", this.audio)
                .fluentPut("title", this.title)
                .fluentPut("content", this.content)
                .fluentPut("image", this.image);
    }
}
