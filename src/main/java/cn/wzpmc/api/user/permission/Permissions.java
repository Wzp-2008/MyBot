package cn.wzpmc.api.user.permission;

/**
 * 权限
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午4:48
 */
public enum Permissions {
    /**
     * 普通用户
     * @since 2024/8/1 下午4:49 v0.0.2-dev
     */
    MEMBER(0),
    /**
     * 管理员
     * @since 2024/8/1 下午4:49 v0.0.2-dev
     */
    ADMIN(1),
    /**
     * 群主
     * @since 2024/8/24 19:25 v0.0.6-dev
     */
    OWNER(2);
    final int level;
    Permissions(int level){
        this.level = level;
    }
    public boolean isAdmin(){
        return this.level > 0;
    }
}
