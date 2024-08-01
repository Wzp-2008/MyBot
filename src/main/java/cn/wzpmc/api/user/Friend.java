package cn.wzpmc.api.user;

import cn.wzpmc.api.message.MessageComponent;
import cn.wzpmc.api.user.permission.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 好友
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午8:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class Friend extends IUser{
    public Friend(Long id, String name, Permissions permissions, String nickname, Sex sex, Integer age) {
        super(id, name, permissions, nickname, sex, age);
    }
    @Override
    public void sendMessage(MessageComponent messageComponent) {

    }
}
