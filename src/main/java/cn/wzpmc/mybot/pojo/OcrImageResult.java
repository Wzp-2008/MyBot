package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class OcrImageResult {
    @JSONField(name="texts")
    private ArrayList<TextDetection> texts;
    @JSONField(name="language")
    private String language;
}
