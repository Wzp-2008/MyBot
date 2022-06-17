package cn.wzpmc.mybot.entities.utils;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
public class Pos {
    @JSONField(name="x")
    private Long x;
    @JSONField(name="y")
    private Long y;
}
