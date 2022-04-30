package cn.wzpmc.mybot.interfaces;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.pojo.Command;
import cn.wzpmc.mybot.utils.PluginClassLoader;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/3/31
 */
@EqualsAndHashCode
public abstract class MyBotPlugin {
    private Yaml config = new Yaml();
    /**
     * 当插件被加载时运行
     * @return 此插件是否加载成功
     */
    public abstract boolean onLoad();
    public Bot getBot(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        if(classLoader instanceof PluginClassLoader pluginClassLoader){
            return pluginClassLoader.bot;
        }else{
            return null;
        }
    }
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
    public Command getCommand(String head){
        return new Command(head,this);
    }
    public void getDefaultConfig(){
        File pluginFolder = this.getPluginFolder();
        PluginClassLoader classLoader = this.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("config.yml");
        Logger logger = this.getLogger();
        Yaml yaml = new Yaml();
        if (resourceAsStream == null) {
            logger.error("没有找到默认配置文件！");
            return;
        }
        File configFile = new File(pluginFolder, "config.yml");
        try {
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
            resourceAsStream.close();
            fileWriter.write(builder.toString());
            yaml.load(new FileReader(configFile, StandardCharsets.UTF_8));
            fileWriter.close();

        } catch (IOException e) {
            logger.error("创建默认配置文件失败！");
            return;
        }
        this.config = yaml;
    }
    public void reloadConfig(){
        getDefaultConfig();
    }
    public Yaml getConfig(){
        return this.config;
    }
    public File getPluginFolder(){
        File plugins = new File("plugins");
        String name = this.getClassLoader().pluginName;
        File folder = new File(plugins,name);
        if(!folder.exists()){
            boolean mkdir = folder.mkdir();
        }
        return folder;
    }
    public PluginClassLoader getClassLoader(){
        return (PluginClassLoader) this.getClass().getClassLoader();
    }
    public Logger getLogger(){
        return LoggerFactory.getLogger(this.getClass());
    }
}
