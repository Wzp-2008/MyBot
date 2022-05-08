package cn.wzpmc.mybot;


import cn.wzpmc.mybot.interfaces.CommandExecutor;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;
import cn.wzpmc.mybot.pojo.Command;
import cn.wzpmc.mybot.pojo.Console;
import cn.wzpmc.mybot.utils.PluginClassLoader;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.*;

import static cn.wzpmc.mybot.constants.StringConstants.*;

/**
 * @author 33572
 * @date 2022/03/29 20:54
 * @version 1.0.0
 * 启动器
 */
public class Main {
    public static Bot bot;
    public static URL http;
    public static Runtime runtime = Runtime.getRuntime();
    public static ArrayList<MyBotPlugin> plugins = new ArrayList<>();
    public static NettyThread nettyThread;
    private static final Logger log = LoggerFactory.getLogger("MyBot");
    public static boolean running = true;

    /**
     * 加载所有在插件文件夹中的jar插件
     * @param bot 机器人对象
     */
    @SneakyThrows
    public static void loadPlugins(Bot bot){
        File pluginsFile = new File("plugins");
        if (!pluginsFile.exists()) {
            boolean isSuccess = pluginsFile.mkdir();
            if (!isSuccess){
                log.error("创建插件文件夹失败！");
                runtime.exit(1);
            }else{
                log.info("插件文件夹创建成功");
            }
        }
        HashMap<MyBotPlugin, String> pluginName;
        for (File file : Objects.requireNonNull(pluginsFile.listFiles())) {
            if(!file.isFile() || !file.getName().contains(".jar")){
                continue;
            }
            pluginName = loadPlugin(file,bot);
            if (pluginName == null){
                log.error("插件 {} 加载失败",file.getName());
            }else{
                for (MyBotPlugin myBotPlugin : pluginName.keySet()) {
                    String name = pluginName.get(myBotPlugin);
                    try {
                        boolean b = myBotPlugin.onLoad();
                        if (b) {
                            plugins.add(myBotPlugin);
                            log.info("插件 {} 成功加载！", name);
                        } else {
                            log.error("插件 {} 加载失败！", name);
                        }
                    }catch (Throwable e){
                        e.printStackTrace();
                        log.error("插件 {} 加载失败！", name);
                    }
                }
            }
        }
    }

    /**
     * 获取op列表
     * @return op列表
     */
    @SneakyThrows
    public static ArrayList<Long> getOps(){
        File ops = new File("ops.json");
        if(!ops.exists()){
            boolean isSuccess = ops.createNewFile();
            if(!isSuccess){
                log.error("创建OP表失败，哈哈");
                runtime.exit(1);
            }else {
                FileWriter fileWriter = new FileWriter(ops);
                fileWriter.write(new JSONArray().toString());
                fileWriter.close();
                log.info("彳亍");
            }
        }
        FileReader opsFileReader = new FileReader(ops);
        StringBuilder opStr = new StringBuilder();
        int b = opsFileReader.read();
        while(b!=-1){
            opStr.append((char) b);
            b = opsFileReader.read();
        }
        String opStrContent = opStr.toString();
        JSONArray opsObjects = JSON.parseArray(opStrContent);
        opsFileReader.close();
        ArrayList<Long> result = new ArrayList<>();
        for (int i = 0; i < opsObjects.size(); i++) {
            Long aLong = Long.valueOf(opsObjects.getString(i));
            result.add(aLong);
        }
        return result;
    }

    /**
     * 获取配置文件
     * @return 配置文件
     */
    @SneakyThrows
    public static Properties getConfig(){
        File config = new File("config.properties");
        if(!config.exists()){
            boolean isSuccess = config.createNewFile();
            if(!isSuccess){
                log.error("创建默认配置文件失败！");
                runtime.exit(1);
            }else{
                log.info("配置文件创建成功！");
            }
        }
        FileReader configFileReader = new FileReader(config);
        Properties botProperties = new Properties();
        botProperties.load(configFileReader);
        boolean empty = botProperties.isEmpty();
        String notFoundCommandMessage = botProperties.getProperty("not_found_command_message");
        String failedRunMessage = botProperties.getProperty("failed_run_message");
        String ws = botProperties.getProperty("ws");
        String http = botProperties.getProperty("http");
        if(empty || notFoundCommandMessage == null || failedRunMessage == null || ws == null || http == null){
            log.info("检测到配置文件为空，开始生成！");
            botProperties.setProperty("not_found_command_message","不正确的指令");
            botProperties.setProperty("failed_run_message","指令执行失败");
            botProperties.setProperty("ws","此处为你的ws连接地址");
            botProperties.setProperty("http","此处为你的http连接地址");
            FileWriter configFileWriter = new FileWriter(config);
            botProperties.store(configFileWriter,null);
            log.info("默认配置文件生成完成，保存在config.properties，请确认修改完成后再重启此服务端！");
            runtime.exit(0);
        }
        return botProperties;
    }

    /**
     * 加载一个插件
     * @param file 插件文件
     * @param bot 机器人对象
     * @return 这个插件
     */
    public static HashMap<MyBotPlugin,String> loadPlugin(File file, Bot bot){
        try {
            String absolutePath = file.getAbsolutePath();
            URL url = new URL("file:///" + absolutePath);
            URL[] urls = {url};
            PluginClassLoader pluginClassLoader = new PluginClassLoader(urls);
            InputStream pluginMetaDataInputStream = pluginClassLoader.getResourceAsStream("plugin.properties");
            Properties metadata = new Properties();
            metadata.load(pluginMetaDataInputStream);
            assert pluginMetaDataInputStream != null;
            pluginMetaDataInputStream.close();
            HashMap<MyBotPlugin, String> plugin = new HashMap<>(1);
            String pluginName = metadata.getProperty("pluginName");
            String pluginMainClassPath = metadata.getProperty("pluginMainClass");
            Class<?> pluginMainClass;
            try {
                pluginMainClass = pluginClassLoader.loadClass(pluginMainClassPath);
            } catch (ClassNotFoundException e) {
                log.error("未找到插件 {} 的主类 {}", pluginName, pluginMainClassPath);
                return null;
            }
            boolean isMyBotPlugin = MyBotPlugin.class.isAssignableFrom(pluginMainClass);
            if (!isMyBotPlugin) {
                log.error("插件 {} 的主类 {} 未继承 MyBotPlugin", pluginName, pluginMainClassPath);
                return null;
            }
            MyBotPlugin o = (MyBotPlugin) pluginMainClass.getDeclaredConstructor().newInstance();
            pluginClassLoader.plugin = o;
            pluginClassLoader.bot = bot;
            pluginClassLoader.pluginName = pluginName;
            pluginClassLoader.logger = LoggerFactory.getLogger(pluginName);
            plugin.put(o, pluginName);
            return plugin;
        }catch (Throwable e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 启动控制台
     */
    public static void consoleRun(){
        Scanner scanner = new Scanner(System.in);
        Console console = new Console();
        while (running){
            String command = scanner.next();
            String[] s = command.split(" ");
            int len = s.length;
            if(len == 0){
                log.error("错误的命令！");
            }else{
                String a0 = s[0];
                CommandExecutor runCommand = null;
                Map<Command, CommandExecutor> consoleCommands = bot.getConsoleCommands();
                Set<Command> commands = consoleCommands.keySet();
                String head = "";
                for (Command command1 : commands) {
                    head = command1.getHead();
                    if(a0.equals(head)){
                        runCommand = consoleCommands.get(command1);
                        break;
                    }
                }
                Command run = new Command(head,new ArrayList<>(Arrays.asList(s)),null);
                if(runCommand != null){
                    runCommand.execute(run,console);
                }else if(STOP.equals(a0)){
                    running = false;
                    log.info("停止");
                    Runtime.getRuntime().exit(0);
                }else if(OP.equals(a0)){
                    if(len == 2){
                        try {
                            Long qq = Long.valueOf(s[1]);
                            Bot.addOp(qq);
                            log.info("成功将用户" + qq + "添加至op列表");
                        }catch (NumberFormatException e){
                            log.error("错误的参数！");
                        }
                    }else{
                        log.info("错误的命令");
                    }
                }else if(DEOP.equals(a0)){
                    if(len == 2){
                        try {
                            Long qq = Long.valueOf(s[1]);
                            Bot.removeOp(qq);
                            log.info("成功将用户" + qq + "移除op");
                        }catch (NumberFormatException e){
                            log.error("错误的参数！");
                        }
                    }else{
                        log.info("错误的命令");
                    }
                }else if(OPS.equals(a0)){
                    log.info("OP列表：");
                    for (Long op : Bot.getOps()) {
                        log.info(String.valueOf(op));
                    }
                }else{
                    log.info("错误的命令！");
                }
            }
        }
    }
    /**
     * 启动
     */
    public static void start(){
        Properties properties = getConfig();
        try {
            http = new URL(properties.getProperty("http"));
        } catch (MalformedURLException e) {
            log.error("url转换失败，请确认你的配置文件中url参数是否错误！");
        }
        ArrayList<Long> ops = getOps();
        bot = new Bot(log,http,ops);
        loadPlugins(bot);
        nettyThread = new NettyThread(properties.getProperty("ws"));
        nettyThread.start();
        Runtime runtime = Runtime.getRuntime();
        StopThread stopThread = new StopThread();
        runtime.addShutdownHook(stopThread);
        consoleRun();
    }

    public static void main(String[] args) {
        start();
    }
}
