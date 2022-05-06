package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.JSONObject;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/2
 */
@ToString
public class GroupUser extends User{
    String sex;
    Integer age;
    public GroupUser(long id, String nickname, String sex, Integer age) {
        super(id, nickname);
        this.sex = sex;
        this.age = age;
    }
    public GroupUser(long id, String nickname) {
        super(id, nickname);
        this.sex = null;
        this.age = null;
    }
    public static GroupUser getGroupUser(JSONObject jsonObject){
        return new GroupUser(jsonObject.getLong("user_id"),jsonObject.getString("nickname"),jsonObject.getString("sex"),jsonObject.getInteger("age"));
    }
}
