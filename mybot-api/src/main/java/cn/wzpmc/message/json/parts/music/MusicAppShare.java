package cn.wzpmc.message.json.parts.music;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 音乐App分享
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MusicAppShare extends Music {
    /**
     * 歌曲 ID
     *
     * @since 2024/8/3 下午6:08 v0.0.3-dev
     */
    private Long id;

    @Override
    public JSONObject getData() {
        return super.getData().fluentPut("id", this.id);
    }
}
