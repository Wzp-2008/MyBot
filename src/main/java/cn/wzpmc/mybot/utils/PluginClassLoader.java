package cn.wzpmc.mybot.utils;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/27
 */
public class PluginClassLoader extends URLClassLoader {
    public MyBotPlugin plugin;
    public Bot bot;
    public String pluginName;

    public PluginClassLoader(URL[] urls) {
        super(urls);
    }

}
