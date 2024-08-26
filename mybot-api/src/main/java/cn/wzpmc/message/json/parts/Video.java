package cn.wzpmc.message.json.parts;

import cn.wzpmc.message.json.JsonMessagePart;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URL;

/**
 * 短视频
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:16
 */
@Data
@NoArgsConstructor
public class Video implements JsonMessagePart {
    /**
     * 视频文件名
     *
     * @since 2024/8/2 下午11:49 v0.0.3-dev
     */
    private String file;
    /**
     * 视频 URL
     *
     * @since 2024/8/2 下午11:49 v0.0.3-dev
     */
    private URL url;
    /**
     * 只在通过网络 URL 发送时有效，表示是否使用已缓存的文件，默认 true
     *
     * @since 2024/8/2 下午11:49 v0.0.3-dev
     */
    private Boolean cache = true;
    /**
     * 只在通过网络 URL 发送时有效，表示是否通过代理下载文件（需通过环境变量或配置文件配置代理），默认 true
     *
     * @since 2024/8/2 下午11:49 v0.0.3-dev
     */
    private Boolean proxy = true;
    /**
     * 只在通过网络 URL 发送时有效，单位秒，表示下载网络文件的超时时间 ，默认不超时
     *
     * @since 2024/8/2 下午11:49 v0.0.3-dev
     */
    private Float timeout = null;

    @Override
    public PartType getPartType() {
        return PartType.VIDEO;
    }

    @Override
    public JSONObject getData() {
        return null;
    }
}
