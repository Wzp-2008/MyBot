package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/30
 */
@ToString
@Getter
@Setter
public class FriendUser extends User{
    private String remark;
    public FriendUser(long id, String nickname,String remark) {
        super(id, nickname);
        this.remark = remark;
    }
    public static FriendUser getFriendUser(JSONObject jsonObject){
        Long userId = jsonObject.getLong("user_id");
        String nickname = jsonObject.getString("nickname");
        String remark = jsonObject.getString("remark");
        return new FriendUser(userId,nickname,remark);
    }
}
