package cn.wzpmc;

import cn.wzpmc.api.api.IMainApi;
import cn.wzpmc.api.api.actions.message.get.GetLoginInfoAction;
import cn.wzpmc.api.events.message.group.GroupMessageEvent;
import cn.wzpmc.api.events.message.priv.PrivateMessageEvent;
import cn.wzpmc.api.message.StringMessage;
import cn.wzpmc.api.plugins.BasePlugin;
import cn.wzpmc.api.plugins.event.EventHandler;
import cn.wzpmc.api.user.Friend;
import cn.wzpmc.api.user.group.GroupCommandSender;
import cn.wzpmc.api.user.group.GroupUser;
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
        File pluginsDir = new File("plugins");
        if (TemplateFileUtils.createDefaultDirectory(pluginsDir)) {
            log.debug("plugin文件夹创建");
        }
        myBot.setPluginsFolder(pluginsDir);
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
        webSocketConnectionHandler.connect(uri);
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
        myBot.registerEventHandler(new Object(){
            @EventHandler
            public void onGroupMessage(GroupMessageEvent event){
                GroupUser sender = event.getSender();
                System.out.println(sender.getId());
                if (sender.getId().equals(3357223099L)) {
                    System.out.println("send");
                    GroupCommandSender.of(event).sendMessage(StringMessage.text("test"));
                    System.out.println("called-group");
                }
            }
            @EventHandler
            public void onUserMessage(PrivateMessageEvent event){
                Friend sender = event.getSender();
                if (sender.getId().equals(3357223099L)){
                    sender.sendMessage(StringMessage.text("test-user"));
                    System.out.println("called-user");
                }
            }
        });
        URI uri = getUriFromConfiguration(configuration);
        if (uri == null){
            log.error("无法解析websocket地址");
            return;
        }
        loadPlugins(myBot);
        WebSocketConnectionHandler webSocketConnectionHandler = createConnection(myBot, uri);
        myBot.setConnectionHandler(webSocketConnectionHandler);
        IMainApi mainApi = myBot.getMainApi();
        System.out.println(mainApi.doApiCall(new GetLoginInfoAction()));
        System.out.println(mainApi.doApiCall(new GetLoginInfoAction()));
        System.out.println(mainApi.doApiCall(new GetLoginInfoAction()));
        startConsole(myBot, webSocketConnectionHandler);
    }
}