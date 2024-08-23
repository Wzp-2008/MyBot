package cn.wzpmc.api.plugins;

import cn.wzpmc.api.user.IBot;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.InputStream;

/**
 * 插件基类
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/7/31 下午6:02
 */
public interface BasePlugin {
    /**
     * 获取插件主类
     * @author wzp
     * @since 2024/7/31 下午7:07 v0.0.2-dev
     * @param pluginClass 插件主类类名
     * @return 插件主类
     * @param <T> 插件主类类型
     */
    static <T extends BasePlugin> T getPlugin(Class<T> pluginClass){
        ClassLoader loader = pluginClass.getClassLoader();
        if (loader instanceof IPluginClassLoader){
            BasePlugin plugin = ((IPluginClassLoader) loader).getPlugin();
            if (pluginClass.isInstance(plugin)) {
                return pluginClass.cast(plugin);
            }
        }
        throw new RuntimeException(new IllegalAccessException("You shouldn't load plugin class without PluginClassLoader!!!"));
    }
    /**
     * 获取Bot
     * @author wzp
     * @since 2024/7/31 下午7:06 v0.0.2-dev
     * @return Bot对象
     */
    default IBot getBot() {
        IPluginClassLoader classLoader = this.getClassLoader();
        return classLoader.getBot();
    }

    /**
     * 获取插件所使用的类加载器
     * @author wzp
     * @since 2024/7/31 下午7:11 v0.0.2-dev
     * @return 类加载器
     */
    IPluginClassLoader getClassLoader();

    /**
     * 当插件被加载时调用
     * @author wzp
     * @since 2024/8/16 13:14 v0.0.5-dev
     */
    void onLoad();

    /**
     * 当插件被卸载时调用
     * @author wzp
     * @since 2024/8/16 13:14 v0.0.5-dev
     */
    void onUnload();

    /**
     * 获取日志记录器
     * @author wzp
     * @since 2024/8/16 13:14 v0.0.5-dev
     * @return 日志记录器
     */
    Logger getLogger();

    /**
     * 从插件中读取资源
     * @author wzp
     * @since 2024/8/16 13:14 v0.0.5-dev
     * @param name 资源路径
     * @return 资源流
     */
    InputStream getResourceAsStream(String name);

    /**
     * 获取插件数据文件夹
     * @author wzp
     * @since 2024/8/16 13:16 v0.0.5-dev
     * @return 插件数据文件夹
     */
    File getDataFolder();

    /**
     * 获取默认配置文件
     * @author wzp
     * @since 2024/8/16 13:16 v0.0.5-dev
     * @return 获取默认配置文件
     */
    File getDefaultConfigFile();

    /**
     * 将插件默认配置文件保存到文件夹中
     * @author wzp
     * @since 2024/8/16 13:16 v0.0.5-dev
     */
    void saveDefaultConfig();
}
