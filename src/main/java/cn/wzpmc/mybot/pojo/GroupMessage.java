package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.cq.At;
import cn.wzpmc.mybot.enums.GroupMessageSubTypes;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/10
 */
@AllArgsConstructor
@Data
@ToString
public class GroupMessage {
    private Integer id;
    private GroupUser sender;
    private Anonymous anonymous;
    private String content;
    private GroupMessageSubTypes subTypes;
    private Integer font;
    private final Bot bot;
    private Long groupId;
    public GroupMessage(JSONObject object,Bot bot){
        this.id = object.getInteger("message_id");
        JSONObject sender = object.getJSONObject("sender");
        this.sender = GroupUser.getGroupUser(sender);
        this.anonymous = object.getObject("anonymous",Anonymous.class);
        this.content = object.getString("message");
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
        At at = sender.getAt();
        String s = at.toString();
        return api.sendGroupMessage(this.groupId, s + message);
    }
}
