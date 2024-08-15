package cn.wzpmc.api.plugins;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Java插件基类
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/7/31 下午7:01
 */
@Setter
@Getter
public abstract class JavaPlugin implements BasePlugin {
    private Logger logger;
    @Override
    public IPluginClassLoader getClassLoader() {
        Class<? extends JavaPlugin> aClass = this.getClass();
        ClassLoader loader = aClass.getClassLoader();
        if (loader instanceof IPluginClassLoader){
            return (IPluginClassLoader) loader;
        }
        throw new RuntimeException(new IllegalAccessException("You shouldn't load plugin class without PluginClassLoader!!!"));
    }

    @Override
    public Logger getLogger() {
        IPluginClassLoader classLoader = this.getClassLoader();
        String name = classLoader.getName();
        String version = classLoader.getVersion();
        if (this.logger == null){
            this.logger = LogManager.getLogger(name + "-" + version);
        }
        return this.logger;
    }
}
