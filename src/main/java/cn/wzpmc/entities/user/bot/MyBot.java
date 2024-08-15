package cn.wzpmc.entities.user.bot;

import cn.wzpmc.api.events.Event;
import cn.wzpmc.api.message.MessageComponent;
import cn.wzpmc.api.message.StringMessage;
import cn.wzpmc.api.message.json.JsonMessage;
import cn.wzpmc.api.plugins.BasePlugin;
import cn.wzpmc.api.user.IBot;
import cn.wzpmc.api.user.permission.Permissions;
import cn.wzpmc.api.utils.IncreasbleHashMap;
import cn.wzpmc.configuration.Configuration;
import cn.wzpmc.console.MyBotConsole;
import cn.wzpmc.entities.event.EventHandlerMethod;
import cn.wzpmc.plugins.CommandManager;
import cn.wzpmc.plugins.PluginManager;
import cn.wzpmc.utils.ReflectionUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 机器人实现类
 * @author wzp
 * @since 2024/7/30 下午11:46
 * @version 0.0.1-dev
 */
@Log4j2
@Getter
public class MyBot extends IBot {
    private final Configuration configuration;
    @Setter
    private Long id;
    @Setter
    private String name;
    private final CommandManager commandManager = new CommandManager(this);
    private final PluginManager pluginManager = new PluginManager();
    private final IncreasbleHashMap<Class<? extends Event>, EventHandlerMethod> events = new IncreasbleHashMap<>();
    @Setter
    private MyBotConsole console = null;
    public MyBot(Configuration configuration){
        this.configuration = configuration;
        this.permissions = Permissions.ADMIN;
    }

    @Override
    public void sendMessage(MessageComponent messageComponent) {
        if (messageComponent instanceof StringMessage){
            log.info(messageComponent.toMessageString());
        }
        if (messageComponent instanceof JsonMessage){
            log.info(((JsonMessage) messageComponent).toTextDisplay());
        }
    }

    @Override
    public void stop() {
        for (BasePlugin plugin : this.pluginManager.getPlugins()) {
            plugin.onUnload();
        }
        if (this.console != null) {
            this.console.shutdown();
        }
    }

    @Override
    public void registerEventHandler(Object handler) {
        this.events.addAll(ReflectionUtils.loadEvents(handler));
    }

    @Override
    public void triggerEvent(Event event) throws InvocationTargetException, IllegalAccessException {
        List<EventHandlerMethod> eventHandlerMethods = this.events.get(event.getClass());
        for (EventHandlerMethod eventHandlerMethod : eventHandlerMethods) {
            eventHandlerMethod.getMethod().invoke(eventHandlerMethod.getObject(), event);
        }
    }
}
