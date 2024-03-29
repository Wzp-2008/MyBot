package cn.wzpmc.mybot.entities.messages;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.entities.cq.At;
import cn.wzpmc.mybot.entities.infos.Anonymous;
import cn.wzpmc.mybot.entities.users.GroupUser;
import cn.wzpmc.mybot.entities.users.PrivateUser;
import cn.wzpmc.mybot.enums.MessageType;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
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

    @JSONCreator
    public GroupMessage(@JSONField(name = "message_id") Integer messageId,
                        @JSONField(name = "user_id") Long userId,
                        String message,
                        @JSONField(name = "raw_message") String rawMessage,
                        Integer font,
                        JSONObject sender,
                        @JSONField(name = "group_id") Long groupId,
                        JSONObject anonymous) {
        super(MessageType.group, message, sender.to(GroupUser.class));
        this.messageSeq = 0L;
        this.id = messageId;
        this.groupId = groupId;
        if (anonymous != null) {
            this.anonymous = anonymous.to(Anonymous.class);
        }
        this.font = font;
    }

    /**
     * 回复这条消息
     *
     * @param message 要回复的内容
     * @return 消息id
     */
    @Override
    public Integer reply(String message, Bot bot) {
        MyBotApi api = bot.getApi();
        At at = ((GroupUser) this.getSender()).getAt();
        String s = at.toString();
        return api.sendGroupMessage(this.groupId, s + message);
    }
}
