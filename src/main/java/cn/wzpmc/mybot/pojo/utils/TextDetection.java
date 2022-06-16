package cn.wzpmc.mybot.pojo.utils;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class TextDetection {
    @JSONField(name="text")
    private String text;
    @JSONField(name="confidence")
    private Integer confidence;
    @JSONField(name="coordinates")
    private Vec2 coordinates;
}
