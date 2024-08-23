package cn.wzpmc.api.plugins;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 插件管理器
 * @author wzp
 * @version 0.0.4-dev
 * @since 2024/8/6 下午3:19
 */
public interface IPluginManager {
    /**
     * 初始化插件主类
     * @param <T>       插件主类类型
     * @param baseClass 插件主类
     * @param name 插件名称
     * @param version 插件版本
     * @return 这个插件的实例
     * @author wzp
     * @since 2024/8/5 上午12:58 v0.0.4-dev
     * @throws NoSuchMethodException 初始化插件错误
     * @throws InvocationTargetException 初始化插件错误
     * @throws InstantiationException 初始化插件错误
     * @throws IllegalAccessException 初始化插件错误
     */
    <T extends BasePlugin> T initPlugin(Class<T> baseClass, String name, String version) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
    List<BasePlugin> getPlugins();
}
