package cn.wzpmc.mybot.entities.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 7:46
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class GroupUser extends User {
    private String sex;
    private Integer age;
    private String card;
    private String area;
    private String level;
    private String role;
    private String title;
}
