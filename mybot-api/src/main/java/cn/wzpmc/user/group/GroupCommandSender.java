package cn.wzpmc.user.group;

import cn.wzpmc.api.IMainApi;
import cn.wzpmc.api.actions.message.send.SendGroupMessageAction;
import cn.wzpmc.events.message.group.GroupMessageEvent;
import cn.wzpmc.message.MessageComponent;
import cn.wzpmc.message.StringMessage;
import cn.wzpmc.message.json.JsonMessage;
import cn.wzpmc.message.json.JsonMessagePart;
import cn.wzpmc.message.json.parts.At;
import cn.wzpmc.user.CommandSender;
import cn.wzpmc.user.IBot;
import cn.wzpmc.user.Sex;
import cn.wzpmc.user.permission.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

import java.util.List;

/**
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/18 00:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class GroupCommandSender extends GroupUser implements CommandSender {
    private Long groupId;

    public GroupCommandSender(Long id, String name, Permissions permissions, String nickname, Sex sex, Integer age, String card, String area, String level, GroupUserRole role, String title, Long groupId) {
        super(id, name, permissions, nickname, sex, age, card, area, level, role, title);
        this.groupId = groupId;
    }

    /**
     * 将一个群消息事件转为一个群指令执行者
     *
     * @param event 群消息事件
     * @return 指令执行者对象
     * @author wzp
     * @since 2024/8/23 21:42 v0.0.5-dev
     */
    public static GroupCommandSender of(GroupMessageEvent event) {
        GroupUser sender = event.getSender();
        Long eventGroupId = event.getGroupId();
        Long id = sender.getId();
        String name = sender.getName();
        Permissions permissions = sender.getPermissions();
        String nickname = sender.getNickname();
        Sex sex = sender.getSex();
        Integer age = sender.getAge();
        String card = sender.getCard();
        String area = sender.getArea();
        String level = sender.getLevel();
        GroupUserRole role = sender.getRole();
        String title = sender.getTitle();
        IBot instance = IBot.getInstance();
        if (!permissions.isAdmin() && instance.isBotOp(eventGroupId, id)) {
            permissions = Permissions.ADMIN;
        }
        return new GroupCommandSender(id, name, permissions, nickname, sex, age, card, area, level, role, title, eventGroupId);
    }

    @SneakyThrows
    @Override
    public void sendMessage(MessageComponent messageComponent) {
        IBot instance = IBot.getInstance();
        IMainApi mainApi = instance.getMainApi();
        JsonMessage jsonMessage = new JsonMessage();
        List<JsonMessagePart> messageParts = jsonMessage.getMessageParts();
        messageParts.add(new At(this.getId()));
        messageParts.add(StringMessage.text(" "));
        if (messageComponent instanceof StringMessage) {
            messageParts.add((StringMessage) messageComponent);
        }
        if (messageComponent instanceof JsonMessage) {
            List<JsonMessagePart> jsonMessageParts = ((JsonMessage) messageComponent).getMessageParts();
            messageParts.addAll(jsonMessageParts);
        }
        SendGroupMessageAction sendGroupMessageAction = new SendGroupMessageAction(this.groupId, jsonMessage);
        mainApi.doApiCall(sendGroupMessageAction);
    }
}
