package cn.wzpmc.mybot.entities.users;

import com.alibaba.fastjson2.JSONObject;
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
public class PrivateUser extends User {
    private String sex;
    private Integer age;

    @JSONCreator
    public PrivateUser(@JSONField(name = "user_id") long id, String nickname, String sex, Integer age) {
        super(id, nickname);
        this.sex = sex;
        this.age = age;
    }

    public PrivateUser(long id, String nickname) {
        super(id, nickname);
        this.sex = null;
        this.age = null;
    }

    public static PrivateUser getGroupUser(JSONObject jsonObject) {
        return new PrivateUser(jsonObject.getLong("user_id"), jsonObject.getString("nickname"), jsonObject.getString("sex"), jsonObject.getInteger("age"));
    }
}
