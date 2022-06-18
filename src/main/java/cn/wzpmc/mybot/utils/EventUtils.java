package cn.wzpmc.mybot.utils;

import cn.wzpmc.mybot.entities.events.Event;
import cn.wzpmc.mybot.entities.events.bot.BotGetConnectEvent;
import cn.wzpmc.mybot.entities.events.bot.ServerHeartbeatEvent;
import cn.wzpmc.mybot.entities.events.friend.FriendAddEvent;
import cn.wzpmc.mybot.entities.events.friend.FriendAddRequestEvent;
import cn.wzpmc.mybot.entities.events.friend.FriendRecallEvent;
import cn.wzpmc.mybot.entities.events.group.GroupCardUpdateEvent;
import cn.wzpmc.mybot.entities.events.group.GroupHonorChangeEvent;
import cn.wzpmc.mybot.entities.events.group.GroupLuckyKingEvent;
import cn.wzpmc.mybot.entities.events.group.GroupRecallEvent;
import cn.wzpmc.mybot.entities.events.group.admin.GroupAdminSetEvent;
import cn.wzpmc.mybot.entities.events.group.admin.GroupAdminUnSetEvent;
import cn.wzpmc.mybot.entities.events.group.ban.GroupBanEvent;
import cn.wzpmc.mybot.entities.events.group.ban.GroupLiftBanEvent;
import cn.wzpmc.mybot.entities.events.group.decreases.GroupDeceasesKickEvent;
import cn.wzpmc.mybot.entities.events.group.decreases.GroupDeceasesKickMeEvent;
import cn.wzpmc.mybot.entities.events.group.decreases.GroupDecreasesLeaveEvent;
import cn.wzpmc.mybot.entities.events.group.essence.GroupEssenceAddEvent;
import cn.wzpmc.mybot.entities.events.group.essence.GroupEssenceDeleteEvent;
import cn.wzpmc.mybot.entities.events.group.file.GroupFileUploadEvent;
import cn.wzpmc.mybot.entities.events.group.increase.GroupIncreaseApproveEvent;
import cn.wzpmc.mybot.entities.events.group.increase.GroupIncreaseInviteEvent;
import cn.wzpmc.mybot.entities.events.group.message.GroupAnonymousMessageEvent;
import cn.wzpmc.mybot.entities.events.group.message.GroupNormalMessageEvent;
import cn.wzpmc.mybot.entities.events.group.message.GroupNoticeMessageEvent;
import cn.wzpmc.mybot.entities.events.group.request.GroupAddRequestEvent;
import cn.wzpmc.mybot.entities.events.group.request.GroupInviteRequestEvent;
import cn.wzpmc.mybot.entities.events.other.GetOfflineFileEvent;
import cn.wzpmc.mybot.entities.events.other.OtherClientStatusChangeEvent;
import cn.wzpmc.mybot.entities.events.other.PokeMessageEvent;
import cn.wzpmc.mybot.entities.events.privatemessage.PrivateFriendMessageEvent;
import cn.wzpmc.mybot.entities.events.privatemessage.PrivateGroupMessageEvent;
import cn.wzpmc.mybot.entities.events.privatemessage.PrivateGroupSelfEvent;
import cn.wzpmc.mybot.entities.events.privatemessage.PrivateOtherMessageEvent;
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
    public static EventIdentifier connect = getMeta(LIFECYCLE, CONNECT);
    public static EventIdentifier heartbeat = getMeta(HEARTBEAT);
    public static EventIdentifier privateFriendMessage = getMessage(PRIVATE, FRIEND);
    public static EventIdentifier privateGroupMessage = getMessage(PRIVATE, GROUP);
    public static EventIdentifier privateGroupSelfMessage = getMessage(PRIVATE, GROUP_SELF);
    public static EventIdentifier privateOtherMessage = getMessage(PRIVATE, OTHER);
    public static EventIdentifier groupNormalMessage = getMessage(GROUP, NORMAL);
    public static EventIdentifier groupAnonymousMessage = getMessage(GROUP, ANONYMOUS);
    public static EventIdentifier groupNoticeMessage = getMessage(GROUP, NOTICE);
    public static EventIdentifier groupFileUpload = getNotice(GROUP_UPLOAD);
    public static EventIdentifier groupAdminSet = getNotice(GROUP_ADMIN, SET);
    public static EventIdentifier groupAdminUnset = getNotice(GROUP_ADMIN, UNSET);
    public static EventIdentifier groupDecreaseLeave = getNotice(GROUP_DECREASE, LEAVE);
    public static EventIdentifier groupDecreaseKick = getNotice(GROUP_DECREASE, KICK);
    public static EventIdentifier groupDecreaseKickMe = getNotice(GROUP_DECREASE, KICK_ME);
    public static EventIdentifier groupIncreaseApprove = getNotice(GROUP_INCREASE, APPROVE);
    public static EventIdentifier groupIncreaseInvite = getNotice(GROUP_INCREASE, INVITE);
    public static EventIdentifier groupRecall = getNotice(GROUP_RECALL);
    public static EventIdentifier groupBan = getNotice(GROUP_BAN, BAN);
    public static EventIdentifier groupLiftBan = getNotice(GROUP_BAN, LIFT_BAN);
    public static EventIdentifier groupLuckKing = getNotice(NOTIFY, LUCKY_KING);
    public static EventIdentifier groupHonorChange = getNotice(NOTIFY, HONOR);
    public static EventIdentifier groupCardUpdate = getNotice(GROUP_CARD);
    public static EventIdentifier groupAddRequest = getRequest(GROUP, ADD);
    public static EventIdentifier groupInviteRequest = getRequest(GROUP, INVITE);
    public static EventIdentifier groupEssenceAdd = getNotice(ESSENCE, ADD);
    public static EventIdentifier groupEssenceDelete = getNotice(ESSENCE, DELETE);
    public static EventIdentifier otherClientStatusChange = getNotice(CLIENT_STATUS);
    public static EventIdentifier friendAdd = getNotice(FRIEND_ADD);
    public static EventIdentifier friendRecall = getNotice(FRIEND_RECALL);
    public static EventIdentifier pokeMessage = getNotice(NOTIFY, POKE);
    public static EventIdentifier getOfflineFile = getNotice(OFFLINE_FILE);
    public static EventIdentifier friendRequest = getRequest(FRIEND);

    public static void registerAllEvent() {
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
        privateFriendMessage.register(PrivateFriendMessageEvent.class);
        privateGroupMessage.register(PrivateGroupMessageEvent.class);
        privateOtherMessage.register(PrivateOtherMessageEvent.class);
        privateGroupSelfMessage.register(PrivateGroupSelfEvent.class);
        groupEssenceAdd.register(GroupEssenceAddEvent.class);
        groupEssenceDelete.register(GroupEssenceDeleteEvent.class);
        otherClientStatusChange.register(OtherClientStatusChangeEvent.class);
        groupAddRequest.register(GroupAddRequestEvent.class);
        groupInviteRequest.register(GroupInviteRequestEvent.class);
        groupRecall.register(GroupRecallEvent.class);
        friendAdd.register(FriendAddEvent.class);
        friendRecall.register(FriendRecallEvent.class);
        pokeMessage.register(PokeMessageEvent.class);
        groupHonorChange.register(GroupHonorChangeEvent.class);
        groupLuckKing.register(GroupLuckyKingEvent.class);
        groupCardUpdate.register(GroupCardUpdateEvent.class);
        friendRequest.register(FriendAddRequestEvent.class);
        getOfflineFile.register(GetOfflineFileEvent.class);
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
