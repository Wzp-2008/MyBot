package cn.wzpmc.api.api.actions.message.get;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.user.IBot;

/**
 * 获取登录号信息
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:18
 */
public class GetLoginInfoAction extends Action<Void, IBot> {
    /**
     * 获取登录号信息
     * @author wzp
     * @since 2024/8/24 23:55 v0.0.6-dev
     */
    public GetLoginInfoAction() {
        super.setAction(Actions.GET_LOGIN_INFO);
    }
}
