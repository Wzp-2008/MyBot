package cn.wzpmc.mybot.entities.utils;

import cn.wzpmc.mybot.constants.StringConstants;
import cn.wzpmc.mybot.entities.events.Event;
import cn.wzpmc.mybot.utils.EventUtils;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/17 18:10
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class EventIdentifier {
    private String postType;
    private String messageType;
    private String metaEventType;
    private String noticeType;
    private String requestType;
    private String subType;

    public static EventIdentifier getInstanceFromJsonObject(JSONObject jsonObject) {
        String postType = jsonObject.getString("post_type");
        String messageType = jsonObject.getString("message_type");
        String metaEventType = jsonObject.getString("meta_event_type");
        String noticeType = jsonObject.getString("notice_type");
        String requestType = jsonObject.getString("request_type");
        String subType = jsonObject.getString("sub_type");
        return new EventIdentifier(postType, messageType, metaEventType, noticeType, requestType, subType);
    }

    public static EventIdentifier getMessage(String messageType) {
        return new EventIdentifier(StringConstants.MESSAGE, messageType, null, null, null, null);
    }

    public static EventIdentifier getMessage(String messageType, String subType) {
        return new EventIdentifier(StringConstants.MESSAGE, messageType, null, null, null, subType);
    }

    public static EventIdentifier getNotice(String noticeType) {
        return new EventIdentifier(StringConstants.NOTICE, null, null, noticeType, null, null);
    }

    public static EventIdentifier getNotice(String noticeType, String subType) {
        return new EventIdentifier(StringConstants.NOTICE, null, null, noticeType, null, subType);
    }

    public static EventIdentifier getMeta(String metaEventType) {
        return new EventIdentifier(StringConstants.META_EVENT, null, metaEventType, null, null, null);
    }

    public static EventIdentifier getMeta(String metaEventType, String subType) {
        return new EventIdentifier(StringConstants.META_EVENT, null, metaEventType, null, null, subType);
    }

    public static EventIdentifier getRequest(String requestType) {
        return new EventIdentifier(StringConstants.REQUEST, null, null, null, requestType, null);
    }

    public static EventIdentifier getRequest(String requestType, String subType) {
        return new EventIdentifier(StringConstants.REQUEST, null, null, null, requestType, subType);
    }

    public void register(Class<? extends Event> clazz) {
        EventUtils.registerIdentifier(this, clazz);
    }
}
