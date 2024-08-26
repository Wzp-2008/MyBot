package cn.wzpmc.api.actions.message.get;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.entities.VersionInformation;

/**
 * 获取版本信息
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:45
 */
public class GetVersionInfoAction extends Action<Void, VersionInformation> {
    /**
     * 获取版本信息
     *
     * @author wzp
     * @since 2024/8/24 23:55 v0.0.6-dev
     */
    public GetVersionInfoAction() {
        super.setAction(Actions.GET_VERSION_INFO);

    }
}
