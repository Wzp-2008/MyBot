package cn.wzpmc.user;

import cn.wzpmc.user.permission.Permissions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户接口
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/30 下午11:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class IUser extends MessageSender {
    /**
     * 玩家昵称
     *
     * @since 2024/8/1 下午8:34 v0.0.2-dev
     */
    protected String nickname;
    /**
     * 用户性别
     *
     * @since 2024/8/1 下午8:25 v0.0.2-dev
     */
    protected Sex sex;
    /**
     * 用户年龄
     *
     * @since 2024/8/1 下午8:25 v0.0.2-dev
     */
    protected Integer age;

    protected IUser(Long id, String name, Permissions permissions, String nickname, Sex sex, Integer age) {
        super(id, name, permissions);
        this.nickname = nickname;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String getName() {
        return this.nickname;
    }

    @Override
    public void setName(String name) {
        this.nickname = name;
        this.name = name;
    }
}
