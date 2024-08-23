package cn.wzpmc.api.user.group;

import cn.wzpmc.api.api.IMainApi;
import cn.wzpmc.api.api.actions.message.send.SendGroupMessageAction;
import cn.wzpmc.api.events.message.group.GroupMessageEvent;
import cn.wzpmc.api.message.MessageComponent;
import cn.wzpmc.api.message.StringMessage;
import cn.wzpmc.api.message.json.JsonMessage;
import cn.wzpmc.api.message.json.JsonMessagePart;
import cn.wzpmc.api.message.json.parts.At;
import cn.wzpmc.api.user.CommandSender;
import cn.wzpmc.api.user.IBot;
import cn.wzpmc.api.user.Sex;
import cn.wzpmc.api.user.permission.Permissions;
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
    public GroupCommandSender(Long id, String name, Permissions permissions, String nickname, Sex sex, Integer age, String card, String area, String level, GroupUserRole role, String title, Long groupId){
        super(id, name, permissions, nickname, sex, age, card, area, level, role, title);
        this.groupId = groupId;
    }
    private Long groupId;

    @SneakyThrows
    @Override
    public void sendMessage(MessageComponent messageComponent) {
        IBot instance = IBot.getInstance();
        IMainApi mainApi = instance.getMainApi();
        JsonMessage jsonMessage = new JsonMessage();
        List<JsonMessagePart> messageParts = jsonMessage.getMessageParts();
        messageParts.add(new At(this.getId()));
        messageParts.add(StringMessage.text(messageComponent.toMessageString()));
        SendGroupMessageAction sendGroupMessageAction = new SendGroupMessageAction(this.groupId, jsonMessage);
        mainApi.doApiCall(sendGroupMessageAction);
    }

    /**
     * 将一个群消息事件转为一个群指令执行者
     * @author wzp
     * @since 2024/8/23 21:42 v0.0.5-dev
     * @param event 群消息事件
     * @return 指令执行者对象
     */
    public static GroupCommandSender of(GroupMessageEvent event){
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
        return new GroupCommandSender(id, name, permissions, nickname, sex, age, card, area, level, role, title, eventGroupId);
    }
}
