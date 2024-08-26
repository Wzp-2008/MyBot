package cn.wzpmc.message.json.parts;

import cn.wzpmc.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

/**
 * 语音
 *
 * @author MoYiJiangNan
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:16
 */
@Data
@NoArgsConstructor
public class Record implements JsonMessagePart {
    private String file;
    private Boolean magic;
    private URL url;
    private Boolean cache;
    private Boolean proxy;
    private Float timeout;

    @Override
    public PartType getPartType() {
        return PartType.RECORD;
    }

    public JSONObject getData() {
        return new JSONObject()
                .fluentPut("file", this.file)
                .fluentPut("magic", this.magic)
                .fluentPut("url", this.url)
                .fluentPut("cache", this.cache)
                .fluentPut("proxy", this.proxy)
                .fluentPut("timeout", this.timeout);
    }
}
