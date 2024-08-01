package cn.wzpmc.api.plugins;

import cn.wzpmc.api.user.IBot;

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
}
