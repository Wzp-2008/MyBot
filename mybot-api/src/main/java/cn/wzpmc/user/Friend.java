package cn.wzpmc.user;

import cn.wzpmc.api.IMainApi;
import cn.wzpmc.api.actions.message.send.SendPrivateMessageAction;
import cn.wzpmc.message.MessageComponent;
import cn.wzpmc.user.permission.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.SneakyThrows;

/**
 * 好友
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午8:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Friend extends IUser implements CommandSender {
    public Friend(Long id, String name, Permissions permissions, String nickname, Sex sex, Integer age) {
        super(id, name, permissions, nickname, sex, age);
    }

    /**
     * 发送消息
     *
     * @param messageComponent 消息组件
     * @author wzp
     * @since 2024/8/23 21:44 v0.0.5-dev
     */
    @SneakyThrows
    @Override
    public void sendMessage(MessageComponent messageComponent) {
        IBot instance = IBot.getInstance();
        IMainApi mainApi = instance.getMainApi();
        mainApi.doApiCall(new SendPrivateMessageAction(this.id, messageComponent));
    }

    @Override
    public boolean isFriend() {
        return true;
    }
}
