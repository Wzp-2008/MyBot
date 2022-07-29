package cn.wzpmc.mybot.utils;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.interfaces.BaseMyBotPlugin;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author wzp
 * @version 1.0.0
 * 插件类加载器
 */
public class PluginClassLoader extends URLClassLoader {
    public BaseMyBotPlugin plugin;
    public Bot bot;
    public String pluginName;
    public Logger logger;
    public JSONObject config;
    public PluginClassLoader(URL[] urls) {
        super(urls);
    }

}
