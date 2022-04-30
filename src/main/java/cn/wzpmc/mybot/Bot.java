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
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
 * @date 2022/3/31
 */
@Slf4j
public class Bot{
    private final ConcurrentHashMap<MyBotPlugin,ConcurrentHashMap<Class<?>,Method>> events = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Command, CommandExecutor> commands = new ConcurrentHashMap<>();
    private final Logger logger;
    private final GroupUser groupBot;
    private final ChannelUser channelBot;
    private final MyBotApi api;
    private final URL http;
    private static ArrayList<Long> ops;
    public At at;
    public Bot(Logger logger, URL http,ArrayList<Long> opsIn){
        this.logger = logger;
        this.http = http;
        this.api = new MyBotApi(this);
        this.groupBot = this.api.getGroupBotInfo();
        this.channelBot = this.api.getChannelBotInfo();
        this.at = new At(groupBot.getId());
        ops = opsIn;
    }
    public URL getHttpUrl(){
        return this.http;
    }
    public MyBotApi getApi(){
        return this.api;
    }
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
    public void registerCommand(Command command,CommandExecutor executor){
        commands.put(command,executor);
    }
    public ConcurrentHashMap<MyBotPlugin, ConcurrentHashMap<Class<?>, Method>> getEvents(){
        return events;
    }
    public Map<Command, CommandExecutor> getCommands(){
        return commands;
    }
    public GroupUser getGroupBot(){
        return this.groupBot;
    }
    public ChannelUser getChannelBot(){
        return this.channelBot;
    }
    public static boolean isOp(Long qq){
        for (Long op : ops) {
            if(Objects.equals(op, qq)){
               return true;
            }
        }
        return false;
    }
    public static ArrayList<Long> getOps(){
        return ops;
    }
    public static void addOp(Long qq){
        ops.add(qq);
        writeOpIntoFile();
    }
    public static void removeOp(Long qq){
        ops.remove(qq);
        writeOpIntoFile();
    }
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
