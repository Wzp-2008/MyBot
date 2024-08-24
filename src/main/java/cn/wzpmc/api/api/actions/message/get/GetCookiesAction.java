package cn.wzpmc.api.api.actions.message.get;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.entities.CookiesInformation;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取 Cookies
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:43
 */
public class GetCookiesAction extends Action<GetCookiesAction.Params, CookiesInformation> {
    /**
     * 获取 Cookies
     * @author wzp
     * @since 2024/8/24 23:51 v0.0.6-dev
     * @param domain 需要获取 cookies 的域名
     */
    public GetCookiesAction(String domain) {
        super.setAction(Actions.GET_COOKIES);
        super.setParams(new Params(domain));
    }

    /**
     * 获取 Cookies
     * @author wzp
     * @since 2024/8/24 23:51 v0.0.6-dev
     */
    public GetCookiesAction() {
        this("");
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 需要获取 cookies 的域名
         * @since 2024/8/24 23:32 v0.0.6-dev
         */
        private String domain;
    }
}
