package cn.wzpmc.plugins;

import cn.wzpmc.user.IBot;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 插件类加载器
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/7/31 下午6:59
 */
public abstract class IPluginClassLoader extends URLClassLoader {
    /**
     * 创建插件类加载器
     *
     * @param urls jar文件路径
     * @author wzp
     * @since 2024/8/23 21:41 v0.0.5-dev
     */
    public IPluginClassLoader(URL[] urls) {
        super(urls);
    }

    /**
     * 获取当前插件
     *
     * @return 插件
     * @author wzp
     * @since 2024/7/31 下午7:15 v0.0.2-dev
     */
    abstract public BasePlugin getPlugin();

    /**
     * 获取Bot
     *
     * @return Bot对象
     * @author wzp
     * @since 2024/7/31 下午7:15 v0.0.2-dev
     */
    abstract public IBot getBot();

    /**
     * 获取插件名称
     *
     * @return 插件名称
     * @author wzp
     * @since 2024/8/8 23:16 v0.0.4-dev
     */
    abstract public String getName();

    /**
     * 获取插件版本
     *
     * @return 版本
     * @author wzp
     * @since 2024/8/8 23:16 v0.0.4-dev
     */
    abstract public String getVersion();
}
