package cn.wzpmc.mybot;

import cn.wzpmc.mybot.annotations.EventHandler;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.cq.At;
import cn.wzpmc.mybot.interfaces.CommandExecutor;
import cn.wzpmc.mybot.interfaces.EventExecutor;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;
import cn.wzpmc.mybot.pojo.ChannelUser;
import cn.wzpmc.mybot.pojo.Command;
import cn.wzpmc.mybot.pojo.GroupUser;
import com.alibaba.fastjson2.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wzp
 * @version 1.0.0
 * 机器人主类
 */
public class Bot{
    private final ConcurrentHashMap<MyBotPlugin,ConcurrentHashMap<Class<?>,Method>> events = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Command, CommandExecutor> commands = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Command, CommandExecutor> consoleCommands = new ConcurrentHashMap<>();
    private final Logger logger;
    private final GroupUser groupBot;
    private final ChannelUser channelBot;
    private final MyBotApi api;
    private final URL http;
    private static ArrayList<Long> ops;
    public At at;
    private static final Logger log = LoggerFactory.getLogger("MyBot");
    public Bot(Logger logger, URL http,ArrayList<Long> opsIn){
        this.logger = logger;
        this.http = http;
        this.api = new MyBotApi(this);
        this.groupBot = this.api.getGroupBotInfo();
        this.channelBot = this.api.getChannelBotInfo();
        this.at = new At(groupBot.getId());
        ops = opsIn;
    }

    /**
     * 获取连接到服务器的http连接地址
     * @return URL地址
     */
    public URL getHttpUrl(){
        return this.http;
    }

    /**
     * 获取可供机器人调用的api
     * @return api
     */
    public MyBotApi getApi(){
        return this.api;
    }

    /**
     * 注册一个事件
     * @param plugin 插件
     * @param executor 指令执行器
     */
    public void registerEvent(MyBotPlugin plugin, EventExecutor executor){
        ConcurrentHashMap<Class<?>, Method> eventsThis = events.getOrDefault(plugin, new ConcurrentHashMap<>(1));
        Class<? extends EventExecutor> eventClass = executor.getClass();
        //获取被注解EventHandler修饰的方法
        boolean flag = false;
        for (Method method : eventClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                flag = true;
                //method是否为static方法
                if (Modifier.isStatic(method.getModifiers())) {
                    //获取method的参数
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        logger.error("插件{}中的类{}中{}方法参数错误", plugin.getClassLoader().pluginName, eventClass,method.getName());
                    } else {
                        Class<?> eventClass1 = parameterTypes[0];
                        eventsThis.put(eventClass1, method);
                    }
                }else{
                    logger.error("插件{}中的类{}中{}方法没有被静态修饰", plugin.getClassLoader().pluginName, eventClass,method.getName());
                }
            }
        }
        events.put(plugin,eventsThis);
        if(!flag){
            logger.error("插件{}中的类{}中没有被EventHandler注解修饰的方法",plugin.getClassLoader().pluginName,eventClass);
        }
    }

    /**
     * 注册一个命令
     * @param command 命令
     * @param executor 命令执行器
     */
    public void registerCommand(Command command,CommandExecutor executor){
        commands.put(command,executor);
    }

    /**
     * 注册一个控制台命令
     * @param command 命令
     * @param executor 命令执行器
     */
    public void registerConsoleCommand(Command command,CommandExecutor executor){
        consoleCommands.put(command,executor);
    }

    /**
     * 在注册命令的同时注册控制台命令
     * @param command 命令
     * @param executor 命令执行器
     */
    public void registerCommandWithConsole(Command command,CommandExecutor executor){
        commands.put(command,executor);
        consoleCommands.put(command,executor);
    }

    /**
     * 获取所有注册的事件
     * @return 所有的事件
     */
    public ConcurrentHashMap<MyBotPlugin, ConcurrentHashMap<Class<?>, Method>> getEvents(){
        return events;
    }

    /**
     * 获取所有注册的命令
     * @return 所有的命令
     */
    public Map<Command, CommandExecutor> getCommands(){
        return commands;
    }

    /**
     * 获取所有注册的控制台命令
     * @return 所有控制台命令
     */
    public Map<Command,CommandExecutor> getConsoleCommands() {
        return consoleCommands;
    }
    /**
     * 获取群中的机器人用户
     * @return 机器人用户
     */
    public GroupUser getGroupBot(){
        return this.groupBot;
    }

    /**
     * 获取频道中的机器人用户
     * @return 机器人用户
     */
    public ChannelUser getChannelBot(){
        return this.channelBot;
    }

    /**
     * 判断一个用户是否为op
     * @param qq 这个用户的qq号
     * @return 这个用户是否为qq
     */
    public static boolean isOp(Long qq){
        for (Long op : ops) {
            if(Objects.equals(op, qq)){
               return true;
            }
        }
        return false;
    }

    /**
     * 获取op列表
     * @return 所有的op的qq号
     */
    public static ArrayList<Long> getOps(){
        return ops;
    }

    /**
     * 将用户添加到op列表
     * @param qq 要添加op的用户qq号
     */
    public static void addOp(Long qq){
        ops.add(qq);
        writeOpIntoFile();
    }

    /**
     * 移除一个用户的op
     * @param qq 要移除op的用户的qq号
     */
    public static void removeOp(Long qq){
        ops.remove(qq);
        writeOpIntoFile();
    }

    /**
     * 将内存中的op列表写入到文件中
     */
    private static void writeOpIntoFile(){
        ArrayList<Long> ops = getOps();
        String s = JSON.toJSONString(ops);
        File opsFile = new File("ops.json");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(opsFile);
            fileOutputStream.write(s.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (IOException e){
            log.error("写入OP表时出错！");
        }
    }
}
