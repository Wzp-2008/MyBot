package cn.wzpmc.mybot.utils;

import cn.wzpmc.mybot.entities.events.Event;
import cn.wzpmc.mybot.entities.events.bot.BotGetConnectEvent;
import cn.wzpmc.mybot.entities.events.bot.ServerHeartbeatEvent;
import cn.wzpmc.mybot.entities.events.group.admin.GroupAdminSetEvent;
import cn.wzpmc.mybot.entities.events.group.admin.GroupAdminUnSetEvent;
import cn.wzpmc.mybot.entities.events.group.ban.GroupBanEvent;
import cn.wzpmc.mybot.entities.events.group.ban.GroupLiftBanEvent;
import cn.wzpmc.mybot.entities.events.group.decreases.GroupDeceasesKickEvent;
import cn.wzpmc.mybot.entities.events.group.decreases.GroupDeceasesKickMeEvent;
import cn.wzpmc.mybot.entities.events.group.decreases.GroupDecreasesLeaveEvent;
import cn.wzpmc.mybot.entities.events.group.file.GroupFileUploadEvent;
import cn.wzpmc.mybot.entities.events.group.increase.GroupIncreaseApproveEvent;
import cn.wzpmc.mybot.entities.events.group.increase.GroupIncreaseInviteEvent;
import cn.wzpmc.mybot.entities.events.group.message.GroupAnonymousMessageEvent;
import cn.wzpmc.mybot.entities.events.group.message.GroupNormalMessageEvent;
import cn.wzpmc.mybot.entities.events.group.message.GroupNoticeMessageEvent;
import cn.wzpmc.mybot.entities.utils.EventIdentifier;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import static cn.wzpmc.mybot.Main.bot;
import static cn.wzpmc.mybot.constants.StringConstants.*;
import static cn.wzpmc.mybot.entities.utils.EventIdentifier.*;

/**
 * @author wzp
 * @version 1.0.0
 */
public class EventUtils {
    private static final HashMap<EventIdentifier, Class<? extends Event>> IDENTIFIER_CLASS_HASH_MAP = new HashMap<>();

    public static void registerAllEvent() {
        EventIdentifier connect = getMeta(LIFECYCLE, CONNECT);
        EventIdentifier heartbeat = getMeta(HEARTBEAT);
        EventIdentifier groupNormalMessage = getMessage(GROUP, NORMAL);
        EventIdentifier groupAnonymousMessage = getMessage(GROUP, ANONYMOUS);
        EventIdentifier groupNoticeMessage = getMessage(GROUP, NOTICE);
        EventIdentifier groupFileUpload = getNotice(GROUP_UPLOAD);
        EventIdentifier groupAdminSet = getNotice(GROUP_ADMIN, SET);
        EventIdentifier groupAdminUnset = getNotice(GROUP_ADMIN, UNSET);
        EventIdentifier groupDecreaseLeave = getNotice(GROUP_DECREASE, LEAVE);
        EventIdentifier groupDecreaseKick = getNotice(GROUP_DECREASE, KICK);
        EventIdentifier groupDecreaseKickMe = getNotice(GROUP_DECREASE, KICK_ME);
        EventIdentifier groupIncreaseApprove = getNotice(GROUP_INCREASE, APPROVE);
        EventIdentifier groupIncreaseInvite = getNotice(GROUP_INCREASE, INVITE);
        EventIdentifier groupBan = getNotice(GROUP_BAN, BAN);
        EventIdentifier groupLiftBan = getNotice(GROUP_BAN, LIFT_BAN);
        EventIdentifier friendAdd = getNotice(FRIEND_ADD);
        EventIdentifier friendRecall = getNotice(FRIEND_RECALL);
        EventIdentifier friendPoke = getNotice(NOTIFY, POKE);
        EventIdentifier groupLuckKing = getNotice(NOTIFY, LUCKY_KING);
        EventIdentifier groupHonorChange = getNotice(NOTIFY, HONOR);
        EventIdentifier groupCardUpdate = getNotice(GROUP_CARD);
        EventIdentifier getOfflineFile = getNotice(OFFLINE_FILE);
        EventIdentifier friendRequest = getRequest(FRIEND);
        EventIdentifier groupAddRequest = getRequest(GROUP, ADD);
        EventIdentifier groupInviteRequest = getRequest(GROUP, INVITE);
        EventIdentifier otherClientStatusChange = getNotice(CLIENT_STATUS);
        EventIdentifier groupEssenceAdd = getNotice(ESSENCE, ADD);
        EventIdentifier groupEssenceDelete = getNotice(ESSENCE, DELETE);
        groupAdminSet.register(GroupAdminSetEvent.class);
        groupAdminUnset.register(GroupAdminUnSetEvent.class);
        groupDecreaseKick.register(GroupDeceasesKickEvent.class);
        groupDecreaseLeave.register(GroupDecreasesLeaveEvent.class);
        groupDecreaseKickMe.register(GroupDeceasesKickMeEvent.class);
        heartbeat.register(ServerHeartbeatEvent.class);
        connect.register(BotGetConnectEvent.class);
        groupNormalMessage.register(GroupNormalMessageEvent.class);
        groupAnonymousMessage.register(GroupAnonymousMessageEvent.class);
        groupNoticeMessage.register(GroupNoticeMessageEvent.class);
        groupIncreaseApprove.register(GroupIncreaseApproveEvent.class);
        groupIncreaseInvite.register(GroupIncreaseInviteEvent.class);
        groupFileUpload.register(GroupFileUploadEvent.class);
        groupBan.register(GroupBanEvent.class);
        groupLiftBan.register(GroupLiftBanEvent.class);

    }

    public static void registerIdentifier(EventIdentifier identifier, Class<? extends Event> clazz) {
        IDENTIFIER_CLASS_HASH_MAP.put(identifier, clazz);
    }

    @SneakyThrows
    public static void runEvent(EventIdentifier identifier, JSONObject data) {
        Class<? extends Event> eventClass = IDENTIFIER_CLASS_HASH_MAP.get(identifier);
        Constructor<? extends Event> constructor = eventClass.getConstructor(JSONObject.class);
        Event event = constructor.newInstance(data);
        runEvent(event);
    }

    public static void runEvent(Event event) throws InvocationTargetException, IllegalAccessException {
        ConcurrentHashMap<MyBotPlugin, ConcurrentHashMap<Class<?>, Method>> events = bot.getEvents();
        ConcurrentHashMap.KeySetView<MyBotPlugin, ConcurrentHashMap<Class<?>, Method>> myBotPlugins = events.keySet();
        for (MyBotPlugin myBotPlugin : myBotPlugins) {
            ConcurrentHashMap<Class<?>, Method> eventWithMethod = events.get(myBotPlugin);
            Method method = eventWithMethod.get(event.getClass());
            if (method != null) {
                method.invoke(null, event);
            }
        }
    }
}
