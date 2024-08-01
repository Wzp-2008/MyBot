package cn.wzpmc.plugins;

import cn.wzpmc.api.plugins.BasePlugin;
import cn.wzpmc.api.plugins.IPluginClassLoader;

/**
 * Java插件基类
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/7/31 下午7:01
 */
public class JavaPlugin implements BasePlugin {

    @Override
    public IPluginClassLoader getClassLoader() {
        Class<? extends JavaPlugin> aClass = this.getClass();
        ClassLoader loader = aClass.getClassLoader();
        if (loader instanceof IPluginClassLoader){
            return (IPluginClassLoader) loader;
        }
        throw new RuntimeException(new IllegalAccessException("You shouldn't load plugin class without PluginClassLoader!!!"));
    }
}
