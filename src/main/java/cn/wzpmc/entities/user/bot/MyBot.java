package cn.wzpmc.entities.user.bot;

import cn.wzpmc.api.ActionResponse;
import cn.wzpmc.api.IMainApi;
import cn.wzpmc.api.actions.message.get.GetGroupListAction;
import cn.wzpmc.configuration.Configuration;
import cn.wzpmc.console.MyBotConsole;
import cn.wzpmc.entities.GroupInformation;
import cn.wzpmc.entities.Ops;
import cn.wzpmc.entities.event.EventHandlerMethod;
import cn.wzpmc.events.Event;
import cn.wzpmc.message.MessageComponent;
import cn.wzpmc.message.StringMessage;
import cn.wzpmc.message.json.JsonMessage;
import cn.wzpmc.network.WebSocketConnectionHandler;
import cn.wzpmc.plugins.BasePlugin;
import cn.wzpmc.plugins.CommandManager;
import cn.wzpmc.plugins.PluginManager;
import cn.wzpmc.plugins.api.MainApi;
import cn.wzpmc.user.IBot;
import cn.wzpmc.user.permission.Permissions;
import cn.wzpmc.utils.IncreasbleHashMap;
import cn.wzpmc.utils.ReflectionUtils;
import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 机器人实现类
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/30 下午11:46
 */
@Log4j2
@Getter
public class MyBot extends IBot {
    private final Configuration configuration;
    private final CommandManager commandManager = new CommandManager(this);
    private final PluginManager pluginManager = new PluginManager();
    private final IncreasbleHashMap<Class<? extends Event>, EventHandlerMethod> events = new IncreasbleHashMap<>();
    @Getter
    private final Ops ops;
    private final File opFile;
    @Setter
    private Long id;
    @Setter
    private String name;
    private File pluginsFolder;
    @Setter
    private MyBotConsole console = null;
    @Getter
    private IMainApi mainApi;
    private WebSocketConnectionHandler connectionHandler;

    public MyBot(Configuration configuration) {
        Ops opsTmp;
        this.configuration = configuration;
        this.permissions = Permissions.ADMIN;
        this.opFile = new File("ops.json");
        if (!this.opFile.isFile()) {
            opsTmp = new Ops();
            try {
                if (!this.opFile.createNewFile()) {
                    log.error("无法创建OP文件！");
                }
            } catch (IOException e) {
                log.error("创建OP文件失败！", e);
                throw new RuntimeException(e);
            }
        } else {
            try (FileInputStream fis = new FileInputStream(this.opFile)) {
                opsTmp = JSON.parseObject(fis, Ops.class);
            } catch (IOException e) {
                log.error("读取OP文件失败！");
                throw new RuntimeException(e);
            }
        }
        if (opsTmp == null) {
            opsTmp = new Ops();
        }
        this.ops = opsTmp;
    }

    @Override
    public void sendMessage(MessageComponent messageComponent) {
        if (messageComponent instanceof StringMessage) {
            log.info(messageComponent.toMessageString());
        }
        if (messageComponent instanceof JsonMessage) {
            log.info(((JsonMessage) messageComponent).toTextDisplay());
        }
    }

    @Override
    public boolean isFriend() {
        return true;
    }

    @Override
    public boolean isConsole() {
        return true;
    }

    @Override
    public void stop() {
        for (BasePlugin plugin : this.pluginManager.getPlugins()) {
            plugin.onUnload();
        }
        if (this.console != null) {
            this.console.shutdown();
        }
        this.flushOpsFile();
    }

    @Override
    public void registerEventHandler(Object handler) {
        this.events.addAll(ReflectionUtils.loadEvents(handler));
    }

    @Override
    public void triggerEvent(Event event) throws InvocationTargetException, IllegalAccessException {
        List<EventHandlerMethod> eventHandlerMethods = this.events.get(event.getClass());
        if (eventHandlerMethods == null) {
            return;
        }
        for (EventHandlerMethod eventHandlerMethod : eventHandlerMethods) {
            eventHandlerMethod.getMethod().invoke(eventHandlerMethod.getObject(), event);
        }
    }

    public void setPluginsFolder(File pluginsFolder) {
        if (this.pluginsFolder != null) {
            throw new IllegalStateException("This bot already initialized!");
        }
        this.pluginsFolder = pluginsFolder;
    }

    public void setConnectionHandler(WebSocketConnectionHandler connectionHandler) {
        this.connectionHandler = connectionHandler;
        this.mainApi = new MainApi(this, this.connectionHandler);
    }

    @Override
    public void addOp(Long userId) {
        Set<Long> admins = this.ops.getAdmins();
        admins.add(userId);
        this.flushOpsFile();
    }

    @Override
    public void addOp(Long groupId, Long userId) {
        Map<String, List<Long>> admins = this.ops.getGroupAdmins();
        String string = groupId.toString();
        List<Long> longs = admins.getOrDefault(string, new ArrayList<>());
        if (!longs.contains(userId)) {
            longs.add(userId);
            admins.put(string, longs);
            this.flushOpsFile();
        }
    }

    @Override
    public boolean removeOp(Long userId) {
        if (!this.ops.isAdmin(userId)) {
            return false;
        }
        Set<Long> admins = this.ops.getAdmins();
        admins.remove(userId);
        flushOpsFile();
        return true;
    }

    @SneakyThrows
    @Override
    public boolean removeOp(Long groupId, Long userId) {
        boolean groupAdmin = this.ops.isAdmin(groupId, userId);
        boolean admin = this.ops.isAdmin(userId);
        if (!groupAdmin && !admin) {
            return false;
        }
        Map<String, List<Long>> admins = this.ops.getGroupAdmins();
        String string = groupId.toString();
        if (groupAdmin && !admin) {
            List<Long> longs = admins.get(string);
            longs.remove(userId);
            admins.put(string, longs);
        } else {
            ActionResponse<List<GroupInformation>> listActionResponse = this.mainApi.doApiCall(new GetGroupListAction());
            List<GroupInformation> data = listActionResponse.getData();
            this.ops.getAdmins().remove(userId);
            for (GroupInformation groupInformation : data) {
                Long groupInformationGroupId = groupInformation.getGroupId();
                if (groupInformationGroupId.equals(groupId)) {
                    continue;
                }
                String groupIdString = groupInformationGroupId.toString();
                List<Long> orDefault = admins.getOrDefault(groupIdString, new ArrayList<>());
                if (!orDefault.contains(userId)) {
                    orDefault.add(userId);
                }
                admins.put(groupIdString, orDefault);
            }
        }
        flushOpsFile();
        return true;
    }

    private void flushOpsFile() {
        try (FileOutputStream fos = new FileOutputStream(this.opFile)) {
            JSON.writeTo(fos, this.ops);
        } catch (IOException e) {
            log.error("写入OP文件失败！", e);
        }
    }
}
