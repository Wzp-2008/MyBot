package cn.wzpmc.builtin.event;

import cn.wzpmc.api.IMainApi;
import cn.wzpmc.api.actions.message.set.SetGroupAddRequestAction;
import cn.wzpmc.events.request.group.GroupJoinRequestEvent;
import cn.wzpmc.events.request.group.GroupJoinRequestEventSubType;
import cn.wzpmc.plugins.event.EventHandler;
import cn.wzpmc.user.IBot;
import lombok.extern.log4j.Log4j2;

/**
 * 处理自动加群
 *
 * @author wzp
 * @version 1.0.3
 * @since 2024/8/31 23:53
 */
@Log4j2
public class AddGroupEventHandler {
    @EventHandler
    public void onUserInvite(GroupJoinRequestEvent event) {
        IBot instance = IBot.getInstance();
        IMainApi mainApi = instance.getMainApi();
        GroupJoinRequestEventSubType subType = event.getSubType();
        if (subType.equals(GroupJoinRequestEventSubType.INVITE)) {
            mainApi.doApiCallSafe(new SetGroupAddRequestAction(event.getFlag(), GroupJoinRequestEventSubType.INVITE));
            log.info("用户{}邀请你加入群{}，已同意", event.getUserId(), event.getGroupId());
        }
    }
}