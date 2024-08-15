package cn.wzpmc.plugins;

import cn.wzpmc.api.plugins.BasePlugin;
import cn.wzpmc.api.plugins.IPluginManager;
import lombok.Getter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 插件管理器
 * @author wzp
 * @since 2024/8/4 下午2:38
 * @version 0.0.4-dev
 */
@Getter
public class PluginManager implements IPluginManager {
    private final List<BasePlugin> plugins = new ArrayList<>();
    public <T extends BasePlugin> T initPlugin(Class<T> baseClass, String name, String version) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ClassLoader loader = baseClass.getClassLoader();
        if (!(loader instanceof PluginClassLoader)){
            throw new IllegalArgumentException("baseClass " + baseClass.getName() + " must be loaded with plugin class loader!!!");
        }
        PluginClassLoader pluginClassLoader = (PluginClassLoader) loader;
        if (Objects.nonNull(pluginClassLoader.getPlugin())){
            throw new IllegalStateException("baseClass " + baseClass.getName() + " has already been initialization!!!");
        }
        Constructor<T> constructor = baseClass.getConstructor();
        T t = constructor.newInstance();
        pluginClassLoader.setPlugin(t, name, version);
        plugins.add(t);
        return t;
    }
}
