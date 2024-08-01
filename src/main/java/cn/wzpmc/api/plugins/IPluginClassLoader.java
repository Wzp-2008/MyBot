package cn.wzpmc.api.plugins;

import cn.wzpmc.api.user.IBot;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 插件类加载器
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/7/31 下午6:59
 */
public abstract class IPluginClassLoader extends URLClassLoader {

    public IPluginClassLoader(URL[] urls) {
        super(urls);
    }

    /**
     * 获取当前插件
     * @author wzp
     * @since 2024/7/31 下午7:15 v0.0.2-dev
     * @return 插件
     */
    abstract public BasePlugin getPlugin();

    /**
     * 获取Bot
     * @author wzp
     * @since 2024/7/31 下午7:15 v0.0.2-dev
     * @return Bot对象
     */
    abstract public IBot getBot();
}
