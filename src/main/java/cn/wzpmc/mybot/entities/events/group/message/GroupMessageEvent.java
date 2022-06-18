package cn.wzpmc.mybot.entities.events.group.message;

import cn.wzpmc.mybot.entities.events.Event;
import cn.wzpmc.mybot.entities.users.GroupUser;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/17 22:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GroupMessageEvent extends Event {
    private Long time;
    private Integer messageId;
    private Long userId;
    private String message;
    private String rawMessage;
    private Integer font;
    private GroupUser sender;
    private Long groupId;

    public GroupMessageEvent(String eventName, JSONObject data) {
        super(eventName, data);
        this.time = data.getLong("time");
        this.messageId = data.getInteger("message_id");
        this.userId = data.getLong("user_id");
        this.message = data.getString("message");
        this.rawMessage = data.getString("raw_message");
        this.font = data.getInteger("font");
        this.sender = data.getJSONObject("sender").toJavaObject(GroupUser.class);
        this.groupId = data.getLong("group_id");
    }

}
