package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.cq.At;
import cn.wzpmc.mybot.enums.CommandSenderType;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/2
 */
@ToString
public class User extends CommandSender {
    private long id;
    private String nickname;
    public User(long id,String nickname){
        super(CommandSenderType.user);
        this.id = id;
        this.nickname = nickname;
    }
    public long getId(){
        return this.id;
    }
    public String getNickname(){
        return this.nickname;
    }
    public void setNickname(String nickname){
        this.nickname = nickname;
    }
    public void setId(long id){
        this.id = id;
    }
    public At getAt(){
        return new At(this.id);
    }
    public boolean isOp(){
        return Bot.isOp(this.id);
    }
}
