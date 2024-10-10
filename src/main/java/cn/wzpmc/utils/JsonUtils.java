package cn.wzpmc.utils;

import cn.wzpmc.api.ActionResponse;
import cn.wzpmc.api.Actions;
import cn.wzpmc.entities.HonorType;
import cn.wzpmc.events.Event;
import cn.wzpmc.events.message.MessageEvent;
import cn.wzpmc.events.meta.MetaEvent;
import cn.wzpmc.events.notice.NoticeEvent;
import cn.wzpmc.events.notice.essence.EssenceEvent;
import cn.wzpmc.events.notice.notify.NotifyEvent;
import cn.wzpmc.events.request.RequestEvent;
import cn.wzpmc.message.StringMessage;
import cn.wzpmc.message.json.JsonMessage;
import cn.wzpmc.user.Friend;
import cn.wzpmc.user.IBot;
import cn.wzpmc.user.IUser;
import cn.wzpmc.user.group.GroupUser;
import cn.wzpmc.utils.json.action.ActionReader;
import cn.wzpmc.utils.json.action.ActionWriter;
import cn.wzpmc.utils.json.event.*;
import cn.wzpmc.utils.json.honor.HonorWriter;
import cn.wzpmc.utils.json.message.JsonMessageReader;
import cn.wzpmc.utils.json.message.JsonMessageWriter;
import cn.wzpmc.utils.json.message.StringMessageReader;
import cn.wzpmc.utils.json.message.StringMessageWriter;
import cn.wzpmc.utils.json.user.FriendUserReader;
import cn.wzpmc.utils.json.user.GroupUserReader;
import cn.wzpmc.utils.json.user.IBotReader;
import cn.wzpmc.utils.json.user.IUserReader;
import com.alibaba.fastjson2.JSON;

/**
 * JSON相关工具类
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午2:04
 */
public class JsonUtils {
    /**
     * 初始化JSON反序列化相关
     *
     * @author wzp
     * @since 2024/8/23 21:50 v0.0.5-dev
     */
    public static void initWriter() {
        JSON.register(JsonMessage.class, new JsonMessageWriter());
        JSON.register(Actions.class, new ActionWriter());
        JSON.register(HonorType.class, new HonorWriter());
        JSON.register(StringMessage.class, new StringMessageWriter());
    }

    /**
     * 初始化JSON序列化相关
     *
     * @author wzp
     * @since 2024/8/23 21:50 v0.0.5-dev
     */
    public static void initReader() {
        JSON.register(MessageEvent.class, new MessageEventReader());
        JSON.register(MetaEvent.class, new MetaEventReader());
        JSON.register(NotifyEvent.class, new NotifyNoticeEventReader());
        JSON.register(NoticeEvent.class, new NoticeEventReader());
        JSON.register(RequestEvent.class, new RequestEventReader());
        JSON.register(Event.class, new EventReader());
        JSON.register(JsonMessage.class, new JsonMessageReader());
        JSON.register(StringMessage.class, new StringMessageReader());
        JSON.register(ActionResponse.class, new ActionReader());
        JSON.register(IUser.class, new IUserReader());
        JSON.register(IBot.class, new IBotReader());
        JSON.register(Friend.class, new FriendUserReader());
        JSON.register(GroupUser.class, new GroupUserReader());
        JSON.register(EssenceEvent.class, new EssenceEventReader());
    }

}
