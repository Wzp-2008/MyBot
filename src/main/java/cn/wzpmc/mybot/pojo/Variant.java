package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/14
 */
@Data
@EqualsAndHashCode
public class Variant {
    @JSONField(name="model_show	")
    private String modelShow;
    @JSONField(name="need_pay")
    private Boolean needPay;
}
