package cn.wzpmc.mybot.pojo.infos;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
public class GroupAtAllRemain {
    @JSONField(name="can_at_all")
    private Boolean canAtAll;
    @JSONField(name="remain_at_all_count_for_group")
    private Short remainAtAllCountForGroup;
    @JSONField(name="remain_at_all_count_for_uin")
    private Short remainAtAllCountForUni;
}
