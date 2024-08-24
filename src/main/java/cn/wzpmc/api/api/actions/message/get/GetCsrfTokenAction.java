package cn.wzpmc.api.api.actions.message.get;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.entities.CsrfTokenInformation;

/**
 * 获取 CSRF Token
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:44
 */
public class GetCsrfTokenAction extends Action<Void, CsrfTokenInformation> {
    /**
     * 获取 CSRF Token
     * @author wzp
     * @since 2024/8/24 23:52 v0.0.6-dev
     */
    public GetCsrfTokenAction(){
        super.setAction(Actions.GET_CSRF_TOKEN);
    }
}
