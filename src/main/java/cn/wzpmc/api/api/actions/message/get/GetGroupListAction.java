package cn.wzpmc.api.api.actions.message.get;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.entities.GroupInformation;

import java.util.List;

/**
 * 获取群列表
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:42
 */
public class GetGroupListAction extends Action<Void, List<GroupInformation>> {
    /**
     * 获取群列表
     * @author wzp
     * @since 2024/8/24 23:54 v0.0.6-dev
     */
    public GetGroupListAction(){
        super.setAction(Actions.GET_GROUP_LIST);
    }
}
