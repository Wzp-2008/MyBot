package cn.wzpmc.mybot.entities.users;

import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChannelUser extends User {
    @JSONCreator
    public ChannelUser(@JSONField(name = "user_id") long id, String nickname) {
        super(id, nickname);
    }
}
