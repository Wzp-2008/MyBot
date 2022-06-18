package cn.wzpmc.mybot.entities.users;

import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 7:46
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupUser extends User {
    private String sex;
    private Integer age;
    private String card;
    private String area;
    private String level;
    private String role;
    private String title;

    @JSONCreator
    public GroupUser(@JSONField(name = "user_id") Long userId,
                     @JSONField(name = "nickname") String nickName,
                     String sex, Integer age, String card, String area, String level, String role,
                     String title) {
        super(userId, nickName);
        this.sex = sex;
        this.age = age;
        this.card = card;
        this.area = area;
        this.level = level;
        this.role = role;
        this.title = title;
    }
}
