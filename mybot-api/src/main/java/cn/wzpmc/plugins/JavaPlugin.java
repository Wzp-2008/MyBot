package cn.wzpmc.plugins;

import cn.wzpmc.user.IBot;
import com.alibaba.fastjson2.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

/**
 * Java插件基类
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/7/31 下午7:01
 */
@Setter
@Getter
@Log4j2
public abstract class JavaPlugin implements BasePlugin {
    /**
     * 日志处理器
     *
     * @since 2024/8/23 21:42 v0.0.5-dev
     */
    private Logger logger;
    /**
     * 配置文件缓存
     * @since 2024/10/10 09:35 v1.0.3
     */
    private JSONObject config;

    @Override
    public IPluginClassLoader getClassLoader() {
        Class<? extends JavaPlugin> aClass = this.getClass();
        ClassLoader loader = aClass.getClassLoader();
        if (loader instanceof IPluginClassLoader) {
            return (IPluginClassLoader) loader;
        }
        throw new RuntimeException(new IllegalAccessException("You shouldn't load plugin class without PluginClassLoader!!!"));
    }

    @Override
    public Logger getLogger() {
        IPluginClassLoader classLoader = this.getClassLoader();
        String name = classLoader.getName();
        String version = classLoader.getVersion();
        if (this.logger == null) {
            this.logger = LogManager.getLogger(name + "-" + version);
        }
        return this.logger;
    }

    public InputStream getResourceAsStream(String name) {
        return this.getClassLoader().getResourceAsStream(name);
    }

    @Override
    public File getDataFolder() {
        IBot bot = this.getClassLoader().getBot();
        File pluginsFolder = bot.getPluginsFolder();
        File file = new File(pluginsFolder, this.getClassLoader().getName());
        if (!file.isDirectory()) {
            if (!file.mkdir()) {
                log.error("Failed to create plugin data folder");
                return null;
            }
        }
        return file;
    }

    @Override
    public File getDefaultConfigFile() {
        File file = new File(this.getDataFolder(), "config.yml");
        if (!file.isFile()) {
            try {
                if (!file.createNewFile()) {
                    log.error("cannot create default config file");
                    return null;
                }
            } catch (IOException e) {
                log.error(e);
                return null;
            }
        }
        return file;
    }

    @Override
    public void saveDefaultConfig() {
        try (InputStream resourceAsStream = this.getResourceAsStream("config.yml")) {
            if (resourceAsStream == null){
                log.error("config.yml no found");
                return;
            }
            File defaultConfigFile = this.getDefaultConfigFile();
            try (FileOutputStream fileOutputStream = new FileOutputStream(defaultConfigFile)) {
                resourceAsStream.transferTo(fileOutputStream);
            }
        } catch (IOException e) {
            log.error(e);
        }
    }
    public void reloadConfig() {
        File file = new File(this.getDataFolder(), "config.yml");
        if (!file.exists()) {
            saveDefaultConfig();
        }
        try(FileInputStream fis = new FileInputStream(file)) {
            this.config = new Yaml().loadAs(fis, JSONObject.class);
        } catch (IOException e) {
            this.logger.error("加载配置文件时出错！");
            this.logger.throwing(e);
        }
    }
    public JSONObject getConfig() {
        if (this.config == null) {
            reloadConfig();
        }
        return this.config;
    }
}