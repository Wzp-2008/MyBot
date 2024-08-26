package cn.wzpmc.api.actions.message;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;

/**
 * 清理缓存
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:46
 */
public class CleanCacheAction extends Action<Void, Void> {
    /**
     * 清理缓存
     *
     * @author wzp
     * @since 2024/8/24 23:59 v0.0.6-dev
     */
    public CleanCacheAction() {
        super.setAction(Actions.CLEAN_CACHE);
    }
}
