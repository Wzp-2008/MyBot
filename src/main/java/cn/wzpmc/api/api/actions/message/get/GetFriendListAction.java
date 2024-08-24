package cn.wzpmc.api.api.actions.message.get;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.entities.MessageInformation;

import java.util.List;

/**
 * 获取好友列表
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:41
 */
public class GetFriendListAction extends Action<Void, List<MessageInformation>> {
    /**
     * 获取好友列表
     * @author wzp
     * @since 2024/8/24 23:52 v0.0.6-dev
     */
    public GetFriendListAction(){
        super.setAction(Actions.GET_FRIEND_LIST);
    }
}
