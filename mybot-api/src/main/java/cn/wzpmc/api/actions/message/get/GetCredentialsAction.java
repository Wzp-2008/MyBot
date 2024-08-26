package cn.wzpmc.api.actions.message.get;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.entities.CredentialsInformation;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 获取 QQ 相关接口凭证
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:44
 */
public class GetCredentialsAction extends Action<GetCredentialsAction.Params, CredentialsInformation> {
    /**
     * 获取 QQ 相关接口凭证
     *
     * @param domain 需要获取 cookies 的域名
     * @author wzp
     * @since 2024/8/24 23:51 v0.0.6-dev
     */
    public GetCredentialsAction(String domain) {
        super.setAction(Actions.GET_COOKIES);
        super.setParams(new Params(domain));
    }

    /**
     * 获取 QQ 相关接口凭证
     *
     * @author wzp
     * @since 2024/8/24 23:52 v0.0.6-dev
     */
    public GetCredentialsAction() {
        this("");
    }

    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 需要获取 cookies 的域名
         *
         * @since 2024/8/24 23:32 v0.0.6-dev
         */
        private String domain;
    }
}
