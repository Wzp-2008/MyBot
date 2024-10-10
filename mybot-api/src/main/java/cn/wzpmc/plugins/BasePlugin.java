package cn.wzpmc.plugins;

import cn.wzpmc.user.IBot;
import com.alibaba.fastjson2.JSONObject;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.InputStream;

/**
 * 插件基类
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/7/31 下午6:02
 */
public interface BasePlugin {
    /**
     * 获取插件主类
     *
     * @param pluginClass 插件主类类名
     * @param <T>         插件主类类型
     * @return 插件主类
     * @author wzp
     * @since 2024/7/31 下午7:07 v0.0.2-dev
     */
    static <T extends BasePlugin> T getPlugin(Class<T> pluginClass) {
        ClassLoader loader = pluginClass.getClassLoader();
        if (loader instanceof IPluginClassLoader) {
            BasePlugin plugin = ((IPluginClassLoader) loader).getPlugin();
            if (pluginClass.isInstance(plugin)) {
                return pluginClass.cast(plugin);
            }
        }
        throw new RuntimeException(new IllegalAccessException("You shouldn't load plugin class without PluginClassLoader!!!"));
    }

    /**
     * 获取Bot
     *
     * @return Bot对象
     * @author wzp
     * @since 2024/7/31 下午7:06 v0.0.2-dev
     */
    default IBot getBot() {
        IPluginClassLoader classLoader = this.getClassLoader();
        return classLoader.getBot();
    }

    /**
     * 获取插件所使用的类加载器
     *
     * @return 类加载器
     * @author wzp
     * @since 2024/7/31 下午7:11 v0.0.2-dev
     */
    IPluginClassLoader getClassLoader();

    /**
     * 当插件被加载时调用
     *
     * @author wzp
     * @since 2024/8/16 13:14 v0.0.5-dev
     */
    void onLoad();

    /**
     * 当插件被卸载时调用
     *
     * @author wzp
     * @since 2024/8/16 13:14 v0.0.5-dev
     */
    void onUnload();

    /**
     * 获取日志记录器
     *
     * @return 日志记录器
     * @author wzp
     * @since 2024/8/16 13:14 v0.0.5-dev
     */
    Logger getLogger();

    /**
     * 从插件中读取资源
     *
     * @param name 资源路径
     * @return 资源流
     * @author wzp
     * @since 2024/8/16 13:14 v0.0.5-dev
     */
    InputStream getResourceAsStream(String name);

    /**
     * 获取插件数据文件夹
     *
     * @return 插件数据文件夹
     * @author wzp
     * @since 2024/8/16 13:16 v0.0.5-dev
     */
    File getDataFolder();

    /**
     * 获取默认配置文件
     *
     * @return 获取默认配置文件
     * @author wzp
     * @since 2024/8/16 13:16 v0.0.5-dev
     */
    File getDefaultConfigFile();

    /**
     * 将插件默认配置文件保存到文件夹中
     *
     * @author wzp
     * @since 2024/8/16 13:16 v0.0.5-dev
     */
    void saveDefaultConfig();

    /**
     * 重载配置文件
     * @author wzp
     * @since 2024/10/10 09:36 v1.0.3
     */
    void reloadConfig();

    /**
     * 获取配置文件内容
     * @author wzp
     * @since 2024/10/10 09:37 v1.0.3
     * @return 配置文件内容
     */
    JSONObject getConfig();
}