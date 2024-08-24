package cn.wzpmc.api.user;

import cn.wzpmc.api.api.IMainApi;
import cn.wzpmc.api.events.Event;
import cn.wzpmc.api.plugins.ICommandManager;
import cn.wzpmc.api.plugins.configuration.IConfiguration;
import lombok.Getter;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

/**
 * 机器人接口
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:31
 */
@Getter
public abstract class IBot extends MessageSender implements CommandSender {
    private static IBot instance = null;
    private String nickname;
    protected IBot(){
        if (IBot.instance == null){
            IBot.instance = this;
        }
    }
    /**
     * 获取配置文件
     * @author wzp
     * @since 2024/7/31 上午2:55 v0.0.1-dev
     * @return 配置文件
     */
    public abstract IConfiguration getConfiguration();

    /**
     * 获取指令管理器
     * @author wzp
     * @since 2024/7/31 上午3:42 v0.0.1-dev
     * @return 指令管理器
     */
    public abstract ICommandManager getCommandManager();

    /**
     * 停止Bot运行
     * @author wzp
     * @since 2024/8/1 下午4:57 v0.0.2-dev
     */
    public abstract void stop();

    /**
     * 注册事件执行器
     * @author wzp
     * @since 2024/8/15 23:46 v0.0.4-dev
     * @param handler 事件执行器
     */
    public abstract void registerEventHandler(Object handler);

    /**
     * 触发一个事件
     * @author wzp
     * @since 2024/8/16 00:49 v0.0.4-dev
     * @param event 事件
     * @throws InvocationTargetException 处理时出现错误
     * @throws IllegalAccessException 处理时出现错误
     */
    public abstract void triggerEvent(Event event) throws InvocationTargetException, IllegalAccessException;

    /**
     * 获取插件文件夹
     * @author wzp
     * @since 2024/8/16 12:49 v0.0.5-dev
     * @return 插件文件夹
     */
    public abstract File getPluginsFolder();

    /**
     * 获取api接口
     * @author wzp
     * @since 2024/8/16 17:34 v0.0.5-dev
     * @return api接口
     */
    public abstract IMainApi getMainApi();

    /**
     * 获取bot实例
     * @author wzp
     * @since 2024/8/17 23:19 v0.0.5-dev
     * @return 一个bot实例对象
     */
    public static IBot getInstance(){
        return IBot.instance;
    }
    public void setNickname(String nickname){
        if (this.nickname == null){
            this.nickname = nickname;
            return;
        }
        throw new RuntimeException(new IllegalAccessException("Shouldn't set nickname after initialized"));
    }
    @Override
    public void setId(Long id){
        if (this.id == null){
            this.id = id;
            return;
        }
        throw new RuntimeException(new IllegalAccessException("Shouldn't set id after initialized"));
    }
}
