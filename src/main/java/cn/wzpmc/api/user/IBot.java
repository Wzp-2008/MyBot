package cn.wzpmc.api.user;

import cn.wzpmc.api.plugins.ICommandManager;
import cn.wzpmc.api.plugins.configuration.IConfiguration;

/**
 * 机器人接口
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:31
 */
public abstract class IBot extends CommandSender {
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
}
