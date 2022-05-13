package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.cq.At;
import cn.wzpmc.mybot.enums.GroupMessageSubTypes;
import cn.wzpmc.mybot.enums.MessageType;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/10
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
@ToString
public class GroupMessage extends Message {
    private Integer id;
    private Anonymous anonymous;
    private GroupMessageSubTypes subTypes;
    private Integer font;
    private final Bot bot;
    private Long groupId;
    public GroupMessage(JSONObject object,Bot bot){
        super(MessageType.group,null,null);
        this.id = object.getInteger("message_id");
        JSONObject sender = object.getJSONObject("sender");
        this.setSender(GroupUser.getGroupUser(sender));
        this.anonymous = object.getObject("anonymous",Anonymous.class);
        this.setContent(object.getString("message"));
        this.subTypes = object.getObject("sub_type",GroupMessageSubTypes.class);
        this.font = object.getInteger("font");
        this.groupId = object.getLong("group_id");
        this.bot = bot;
    }

    /**
     * 回复这条消息
     * @param message 要回复的内容
     * @return 消息id
     */
    public Integer reply(String message){
        MyBotApi api = this.getBot().getApi();
        At at = ((GroupUser) this.getSender()).getAt();
        String s = at.toString();
        return api.sendGroupMessage(this.groupId, s + message);
    }
}
