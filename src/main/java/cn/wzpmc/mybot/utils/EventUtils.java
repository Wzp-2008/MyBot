package cn.wzpmc.mybot.utils;

import cn.wzpmc.mybot.Event.Event;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

import static cn.wzpmc.mybot.Main.bot;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/4
 */
public class EventUtils {
    public static void runEvent(Event event) throws InvocationTargetException, IllegalAccessException {
        ConcurrentHashMap<MyBotPlugin, ConcurrentHashMap<Class<?>, Method>> events = bot.getEvents();
        ConcurrentHashMap.KeySetView<MyBotPlugin, ConcurrentHashMap<Class<?>, Method>> myBotPlugins = events.keySet();
        for (MyBotPlugin myBotPlugin : myBotPlugins) {
            ConcurrentHashMap<Class<?>, Method> eventWithMethod = events.get(myBotPlugin);
            Method method = eventWithMethod.get(event.getClass());
            if(method != null){
                method.invoke(null,event);
            }
        }
    }
}
