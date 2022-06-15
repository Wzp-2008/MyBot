package cn.wzpmc.mybot.interfaces;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.pojo.Command;
import cn.wzpmc.mybot.utils.PluginClassLoader;
import com.alibaba.fastjson2.JSONObject;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author wzp
 * @version 1.0.0
 */
@EqualsAndHashCode
public abstract class MyBotPlugin {
    private JSONObject config = new JSONObject();
    /**
     * 当插件被加载时运行
     * @return 此插件是否加载成功
     */
    public abstract boolean onLoad();

    /**
     * 获取Bot
     * @return Bot
     */
    public Bot getBot(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        if(classLoader instanceof PluginClassLoader pluginClassLoader){
            return pluginClassLoader.bot;
        }else{
            return null;
        }
    }

    /**
     * 获取插件
     * @param clazz 插件主类
     * @param <T> 插件主类的类型
     * @return 插件
     */
    public static <T extends MyBotPlugin> T getPlugin(Class<T> clazz) {
        if(!MyBotPlugin.class.isAssignableFrom(clazz)){
            throw new IllegalArgumentException(clazz + " does not extend " + MyBotPlugin.class);
        }
        ClassLoader classLoader = clazz.getClassLoader();
        if(!(classLoader instanceof PluginClassLoader pluginClassLoader)){
            throw new IllegalArgumentException(clazz + " is not initialized by " + PluginClassLoader.class);
        }
        MyBotPlugin plugin = pluginClassLoader.plugin;
        return clazz.cast(plugin);
    }
    /**
     * 获取一个指令
     * @param head 指令的指令头
     * @return 指令
     */
    public Command getCommand(String head){
        return new Command(head,this);
    }

    /**
     * 获取默认配置文件，若配置文件不存在则创建它
     */
    public void getDefaultConfig(){
        File pluginFolder = this.getPluginFolder();
        PluginClassLoader classLoader = this.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("config.yml");
        Logger logger = this.getLogger();
        Yaml yaml = new Yaml();
        JSONObject object;
        if (resourceAsStream == null) {
            logger.error("没有找到默认配置文件！");
            return;
        }
        File configFile = new File(pluginFolder, "config.yml");
        try {
            if(!configFile.exists()){
                boolean newFile = configFile.createNewFile();
                if(!newFile){
                    logger.error("创建默认配置文件失败！");
                    return;
                }
                FileWriter fileWriter = new FileWriter(configFile);
                StringBuilder builder = new StringBuilder();
                byte[] bytes = resourceAsStream.readAllBytes();
                for (byte aByte : bytes) {
                    builder.append((char) aByte);
                }
                fileWriter.write(builder.toString());
                fileWriter.close();
            }
            resourceAsStream.close();
            object = yaml.loadAs(new FileReader(configFile, StandardCharsets.UTF_8), JSONObject.class);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("创建默认配置文件失败！");
            return;
        }
        this.config = object;
    }

    /**
     * 从磁盘重新加载配置文件到内存
     */
    public void reloadConfig(){
        getDefaultConfig();
    }

    /**
     * 从内存中获取配置文件
     *
     * @return Yaml配置文件
     */
    public JSONObject getConfig(){
        return this.config;
    }

    /**
     * 获取配置文件文件夹
     * @return 配置文件文件夹
     */
    public File getPluginFolder(){
        File plugins = new File("plugins");
        String name = this.getClassLoader().pluginName;
        File folder = new File(plugins,name);
        if(!folder.exists()){
            boolean mkdir = folder.mkdir();
        }
        return folder;
    }

    /**
     * 获取插件的类加载器
     * @return 插件的类加载器
     */
    public PluginClassLoader getClassLoader(){
        return (PluginClassLoader) this.getClass().getClassLoader();
    }

    /**
     * 获取日志器
     * @return 日志器
     */
    public Logger getLogger(){
        return this.getClassLoader().logger;
    }
}
