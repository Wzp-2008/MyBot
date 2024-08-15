package cn.wzpmc;

import cn.wzpmc.api.plugins.BasePlugin;
import cn.wzpmc.commands.StopCommand;
import cn.wzpmc.configuration.Configuration;
import cn.wzpmc.console.MyBotConsole;
import cn.wzpmc.entities.user.bot.MyBot;
import cn.wzpmc.network.WebSocketConnectionHandler;
import cn.wzpmc.plugins.CommandManager;
import cn.wzpmc.plugins.PluginClassLoader;
import cn.wzpmc.plugins.PluginManager;
import cn.wzpmc.utils.JsonUtils;
import cn.wzpmc.utils.ReflectionUtils;
import cn.wzpmc.utils.TemplateFileUtils;
import cn.wzpmc.utils.YamlUtils;
import io.netty.channel.ChannelFuture;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Log4j2
public class Main {
    private static final String DEFAULT_CONFIGURATION_FILE_PATH = "templates/config.yaml";
    private static File pluginsDir;
    public static void initializeJVM(){
        System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
        System.setProperty("terminal.jline", "true");
    }
    public static void initializeJsonUtils(){
        JsonUtils.initReader();
        JsonUtils.initWriter();
    }
    public static Configuration getConfiguration() {
        File configurationFile = new File("config.yaml");
        if (TemplateFileUtils.saveDefaultConfig(Main.class.getClassLoader(), DEFAULT_CONFIGURATION_FILE_PATH, configurationFile)) {
            log.debug("创建日志文件成功！");
            log.info("首次启动，默认配置文件已创建，请填写后再次启动MyBot！");
            return null;
        }
        pluginsDir = new File("plugins");
        if (TemplateFileUtils.createDefaultDirectory(pluginsDir)) {
            log.debug("plugin文件夹创建");
        }
        log.debug("读取配置文件 {}", configurationFile.getAbsolutePath());
        return YamlUtils.readYamlFile(configurationFile, Configuration.class);
    }
    public static MyBot createBot(Configuration configuration){
        return new MyBot(configuration);
    }
    public static URI getUriFromConfiguration(Configuration configuration){
        URI uri;
        try {
            uri = new URI(configuration.getWebsocket());
        } catch (URISyntaxException e) {
            return null;
        }
        return uri;
    }
    public static void loadPlugins(MyBot myBot) throws MalformedURLException {
        File[] files = pluginsDir.listFiles();
        if (files == null) {
            log.error("没有权限读取插件目录！");
            return;
        }
        PluginManager pluginManager = myBot.getPluginManager();
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            String name = file.getName();
            if (!name.endsWith(".jar")) {
                continue;
            }
            URI fileURI = file.toURI();
            PluginClassLoader pluginClassLoader = new PluginClassLoader(new URL[]{fileURI.toURL()}, myBot);
            BasePlugin load = ReflectionUtils.load(pluginClassLoader, file, pluginManager);
            if (load == null){
                log.info("插件{}加载失败！", name);
                continue;
            }
            load.onLoad();
        }
        CommandManager commandManager = myBot.getCommandManager();
        commandManager.registerCommand(new StopCommand(myBot));
    }
    public static WebSocketConnectionHandler createConnection(MyBot myBot, URI uri){
        WebSocketConnectionHandler webSocketConnectionHandler = new WebSocketConnectionHandler(myBot);
        ChannelFuture future = webSocketConnectionHandler.connect(uri);
        return webSocketConnectionHandler;
    }
    public static void startConsole(MyBot myBot, WebSocketConnectionHandler webSocketConnectionHandler){
        MyBotConsole myBotConsole = new MyBotConsole(myBot, webSocketConnectionHandler);
        myBotConsole.start();
    }
    @SneakyThrows
    public static void main(String[] args) {
        initializeJVM();
        initializeJsonUtils();
        log.info("启动MyBot...");
        Configuration configuration = getConfiguration();
        if (configuration == null){
            return;
        }
        MyBot myBot = createBot(configuration);
        URI uri = getUriFromConfiguration(configuration);
        if (uri == null){
            log.error("无法解析websocket地址");
            return;
        }
        loadPlugins(myBot);
        WebSocketConnectionHandler webSocketConnectionHandler = createConnection(myBot, uri);
        startConsole(myBot, webSocketConnectionHandler);
    }
}