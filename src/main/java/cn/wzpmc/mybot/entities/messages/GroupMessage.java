package cn.wzpmc.mybot.entities.messages;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.entities.cq.At;
import cn.wzpmc.mybot.entities.infos.Anonymous;
import cn.wzpmc.mybot.entities.users.PrivateUser;
import cn.wzpmc.mybot.enums.MessageType;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@ToString
public class GroupMessage extends BaseMessage {
    private Integer id;
    private Anonymous anonymous;
    private Integer font;
    private Long groupId;
    private Long messageSeq;

    public GroupMessage(JSONObject object) {
        super(MessageType.group, null, null);
        this.id = object.getInteger("message_id");
        JSONObject sender = object.getJSONObject("sender");
        this.setSender(PrivateUser.getGroupUser(sender));
        this.anonymous = object.getObject("anonymous", Anonymous.class);
        this.setContent(object.getString("message"));
        this.font = object.getInteger("font");
        this.groupId = object.getLong("group_id");
        this.messageSeq = object.getLong("message_seq");
    }

    /**
     * 回复这条消息
     * @param message 要回复的内容
     * @return 消息id
     */
    @Override
    public Integer reply(String message,Bot bot){
        MyBotApi api = bot.getApi();
        At at = ((PrivateUser) this.getSender()).getAt();
        String s = at.toString();
        return api.sendGroupMessage(this.groupId, s + message);
    }
}
