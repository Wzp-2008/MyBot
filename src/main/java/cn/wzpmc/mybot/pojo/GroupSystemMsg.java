package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class GroupSystemMsg {
    @JSONField(name="invited_requests")
    private ArrayList<InvitedRequest> invitedRequest;
    @JSONField(name="join_requests")
    private ArrayList<JoinRequest> joinRequest;
}
