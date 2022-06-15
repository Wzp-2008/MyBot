package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
public class JoinRequest {
    @JSONField(name="requestId")
    private Long requestId;
    @JSONField(name="invitor_uin")
    private Long invitorUin;
    @JSONField(name="invitor_nick")
    private String invitorNick;
    @JSONField(name="message")
    private String message;
    @JSONField(name="group_id")
    private Long groupId;
    @JSONField(name="group_name")
    private String groupName;
    @JSONField(name="checked")
    private Boolean checked;
    @JSONField(name="actor")
    private Long actor;
}
