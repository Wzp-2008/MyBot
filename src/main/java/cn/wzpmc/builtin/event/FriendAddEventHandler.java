package cn.wzpmc.builtin.event;

import cn.wzpmc.api.IMainApi;
import cn.wzpmc.api.actions.message.set.SetFriendAddRequestAction;
import cn.wzpmc.events.request.FriendAddRequestEvent;
import cn.wzpmc.plugins.event.EventHandler;
import cn.wzpmc.user.IBot;
import lombok.extern.log4j.Log4j2;

/**
 * 自动通过好友申请相关事件
 *
 * @author wzp
 * @version 1.0.3
 * @since 2024/8/30 15:44
 */
@Log4j2
public class FriendAddEventHandler {
    @EventHandler
    public void onFriendAdd(FriendAddRequestEvent event) {
        String flag = event.getFlag();
        Long userId = event.getUserId();
        IBot instance = IBot.getInstance();
        IMainApi mainApi = instance.getMainApi();
        mainApi.doApiCallSafe(new SetFriendAddRequestAction(flag));
        log.info("已通过{}的好友申请", userId);
    }
}
