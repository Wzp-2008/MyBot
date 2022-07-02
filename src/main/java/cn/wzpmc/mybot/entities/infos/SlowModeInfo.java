package cn.wzpmc.mybot.entities.infos;

import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/7/2 20:13
 */
@Data
@EqualsAndHashCode
public class SlowModeInfo {
    @JSONField(name = "slow_mode_key")
    private Long slowModeKey;
    @JSONField(name = "slow_mode_text")
    private String slowModeText;
    @JSONField(name = "speak_frequency")
    private Integer speakFrequency;
    @JSONField(name = "slow_mode_circle")
    private Integer slowModeCircle;

    @JSONCreator
    public SlowModeInfo(@JSONField(name = "slow_mode_key")
                        Long slowModeKey,
                        @JSONField(name = "slow_mode_text")
                        String slowModeText,
                        @JSONField(name = "speak_frequency")
                        Integer speakFrequency,
                        @JSONField(name = "slow_mode_circle")
                        Integer slowModeCircle) {
        this.slowModeKey = slowModeKey;
        this.slowModeText = slowModeText;
        this.speakFrequency = speakFrequency;
        this.slowModeCircle = slowModeCircle;
    }
}
