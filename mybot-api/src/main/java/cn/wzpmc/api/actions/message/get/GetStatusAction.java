package cn.wzpmc.api.actions.message.get;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.entities.BotStatus;

/**
 * 获取运行状态
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:45
 */
public class GetStatusAction extends Action<Void, BotStatus> {
    /**
     * 获取运行状态
     *
     * @author wzp
     * @since 2024/8/24 23:55 v0.0.6-dev
     */
    public GetStatusAction() {
        super.setAction(Actions.GET_STATUS);
    }
}
