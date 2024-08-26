package cn.wzpmc.utils;

import cn.wzpmc.api.events.Event;
import cn.wzpmc.api.plugins.BasePlugin;
import cn.wzpmc.api.plugins.IPluginManager;
import cn.wzpmc.api.plugins.event.EventHandler;
import cn.wzpmc.api.utils.IncreasbleHashMap;
import cn.wzpmc.api.utils.IncreasbleMap;
import cn.wzpmc.entities.event.EventHandlerMethod;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLClassLoader;
import java.util.List;
import java.util.Optional;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 反射工具类
 * @author wzp
 * @since 2024/8/5 上午1:42
 * @version 0.0.4-dev
 */
@Log4j2
public class ReflectionUtils {
    public static BasePlugin load(URLClassLoader loader, File file, IPluginManager pluginManager){
        String absolutePath = file.getAbsolutePath();
        try(JarFile jarFile = new JarFile(file)){
            Optional<JarEntry> first = jarFile.stream().filter((e) -> "plugin.yml".equals(e.getName())).findFirst();
            if (first.isEmpty()){
                log.error("cannot find plugin.yml in plugin {}", absolutePath);
                return null;
            }
            InputStream stream = loader.getResourceAsStream("plugin.yml");
            Yaml yaml = new Yaml();
            JSONObject jsonObject = yaml.loadAs(stream, JSONObject.class);
            String main = jsonObject.getString("main");
            String version = jsonObject.getString("version");
            String name = jsonObject.getString("name");
            try{
                Class<?> aClass = loader.loadClass(main);
                if (!BasePlugin.class.isAssignableFrom(aClass)) {
                    log.error("插件{}-{}的主类{}未继承cn.wzpmc.api.plugins.JavaPlugin", name, version, main);
                    return null;
                }
                try {
                    //noinspection unchecked
                    BasePlugin basePlugin = pluginManager.initPlugin((Class<? extends BasePlugin>) aClass, name, version);
                    log.info("插件{}-{}已成功加载", name, version);
                    return basePlugin;
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                         IllegalAccessException e) {
                    log.error(e);
                    return null;
                }
            }catch (ClassNotFoundException e) {
                log.error("无法为插件{}-{}加载主类{}！", name, version, main);
                return null;
            }

        } catch (IOException e) {
            log.error(e);
            return null;
        }
    }
    public static IncreasbleMap<Class<? extends Event>, EventHandlerMethod, List<EventHandlerMethod>> loadEvents(Object eventHandlerObject){
        Class<?> eventHandlerClass = eventHandlerObject.getClass();
        IncreasbleMap<Class<? extends Event>, EventHandlerMethod, List<EventHandlerMethod>> result = new IncreasbleHashMap<>();
        for (Method declaredMethod : eventHandlerClass.getDeclaredMethods()) {
            declaredMethod.setAccessible(true);
            if (!declaredMethod.isAnnotationPresent(EventHandler.class)){
                continue;
            }
            if (declaredMethod.getParameterCount() == 1) {
                Class<?> eventType = declaredMethod.getParameterTypes()[0];
                if (Event.class.isAssignableFrom(eventType)){
                    //noinspection unchecked
                    result.add((Class<? extends Event>) eventType, new EventHandlerMethod(eventHandlerObject, declaredMethod));
                    continue;
                }
            }
            log.warn("Not event method {}::{} shouldn't been annotations with @EventHandler", eventHandlerClass, declaredMethod.getName());
        }
        return result;
    }
}
