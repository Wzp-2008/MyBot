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
public class Vec2 {
    @JSONField(ordinal=1)
    private Pos pos1;
    @JSONField(ordinal=2)
    private Pos pos2;
}
