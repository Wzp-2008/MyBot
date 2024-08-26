package cn.wzpmc.message.json.parts.music;

import cn.wzpmc.message.json.JsonMessagePart;
import cn.wzpmc.message.json.parts.PartType;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 音乐分享
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:24
 */
@Data
@NoArgsConstructor
public abstract class Music implements JsonMessagePart {
    /**
     * 音乐类型
     *
     * @since 2024/8/3 下午6:07 v0.0.3-dev
     */
    protected MusicType musicType;

    @Override
    public PartType getPartType() {
        return PartType.MUSIC;
    }

    @Override
    public JSONObject getData() {
        return JSONObject.of("type", this.musicType.name);
    }
}
