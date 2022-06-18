package cn.wzpmc.mybot.entities.users;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.entities.cq.At;
import cn.wzpmc.mybot.enums.MessageSenderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class User extends MessageSender {
    public User(long id,String nickname){
        super(MessageSenderType.user,id,nickname);
    }
    public At getAt(){
        return new At(this.getId());
    }
    public boolean isOp(){
        return Bot.isOp(this.getId());
    }
}
