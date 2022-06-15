package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.cq.At;
import cn.wzpmc.mybot.enums.MessageSenderType;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 */
@ToString
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
