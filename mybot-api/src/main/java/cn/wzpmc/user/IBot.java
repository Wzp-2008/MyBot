package cn.wzpmc.user;

import cn.wzpmc.api.IMainApi;
import cn.wzpmc.entities.Ops;
import cn.wzpmc.events.Event;
import cn.wzpmc.plugins.ICommandManager;
import cn.wzpmc.plugins.configuration.IConfiguration;
import lombok.Getter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * 机器人接口
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:31
 */
@Getter
public abstract class IBot extends MessageSender implements CommandSender {
    private static IBot instance = null;
    private String nickname;

    protected IBot() {
        if (IBot.instance == null) {
            IBot.instance = this;
        }
    }

    /**
     * 获取bot实例
     *
     * @return 一个bot实例对象
     * @author wzp
     * @since 2024/8/17 23:19 v0.0.5-dev
     */
    public static IBot getInstance() {
        return IBot.instance;
    }

    /**
     * 获取配置文件
     *
     * @return 配置文件
     * @author wzp
     * @since 2024/7/31 上午2:55 v0.0.1-dev
     */
    public abstract IConfiguration getConfiguration();

    /**
     * 获取指令管理器
     *
     * @return 指令管理器
     * @author wzp
     * @since 2024/7/31 上午3:42 v0.0.1-dev
     */
    public abstract ICommandManager getCommandManager();

    /**
     * 停止Bot运行
     *
     * @author wzp
     * @since 2024/8/1 下午4:57 v0.0.2-dev
     */
    public abstract void stop();

    /**
     * 注册事件执行器
     *
     * @param handler 事件执行器
     * @author wzp
     * @since 2024/8/15 23:46 v0.0.4-dev
     */
    public abstract void registerEventHandler(Object handler);

    /**
     * 触发一个事件
     *
     * @param event 事件
     * @throws InvocationTargetException 处理时出现错误
     * @throws IllegalAccessException    处理时出现错误
     * @author wzp
     * @since 2024/8/16 00:49 v0.0.4-dev
     */
    public abstract void triggerEvent(Event event) throws InvocationTargetException, IllegalAccessException;

    /**
     * 获取插件文件夹
     *
     * @return 插件文件夹
     * @author wzp
     * @since 2024/8/16 12:49 v0.0.5-dev
     */
    public abstract File getPluginsFolder();

    /**
     * 获取api接口
     *
     * @return api接口
     * @author wzp
     * @since 2024/8/16 17:34 v0.0.5-dev
     */
    public abstract IMainApi getMainApi();

    public void setNickname(String nickname) {
        if (this.nickname == null) {
            this.nickname = nickname;
            return;
        }
        throw new RuntimeException(new IllegalAccessException("Shouldn't set nickname after initialized"));
    }

    @Override
    public void setId(Long id) {
        if (this.id == null) {
            this.id = id;
            return;
        }
        throw new RuntimeException(new IllegalAccessException("Shouldn't set id after initialized"));
    }

    /**
     * 获取OP列表
     *
     * @return OP列表
     * @author wzp
     * @since 2024/8/25 14:11 v1.0.0
     */
    abstract public Ops getOps();

    /**
     * 添加一个OP用户
     *
     * @param userId 用户ID
     * @author wzp
     * @since 2024/8/25 14:12 v1.0.0
     */
    abstract public void addOp(Long userId);

    /**
     * 为一个群添加OP用户
     *
     * @param groupId 群ID
     * @param userId  用户ID
     * @author wzp
     * @since 2024/8/25 20:21 v1.0.0
     */
    abstract public void addOp(Long groupId, Long userId);

    /**
     * 移除一个用户的OP身份
     *
     * @param userId 用户ID
     * @return 是否移除
     * @author wzp
     * @since 2024/8/25 20:23 v1.0.0
     */
    abstract public boolean removeOp(Long userId);

    /**
     * 移除一个用户在群内的OP身份
     *
     * @param groupId 群ID
     * @param userId  用户ID
     * @return 是否移除
     * @author wzp
     * @since 2024/8/25 20:23 v1.0.0
     */
    abstract public boolean removeOp(Long groupId, Long userId);

    /**
     * 检查用户是否为OP
     *
     * @param userId 用户ID
     * @return 是否为OP
     * @author wzp
     * @since 2024/8/25 14:10 v1.0.0
     */
    public boolean isBotOp(Long userId) {
        return getOps().isAdmin(userId);
    }

    /**
     * 检查用户在群内是否为OP
     *
     * @param groupId 群ID
     * @param userId  用户ID
     * @return 是否为OP
     * @author wzp
     * @since 2024/8/25 20:22 v1.0.0
     */
    public boolean isBotOp(Long groupId, Long userId) {
        return this.getOps().isAdmin(groupId, userId);
    }
}
