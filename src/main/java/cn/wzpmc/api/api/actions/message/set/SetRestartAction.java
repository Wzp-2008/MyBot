package cn.wzpmc.api.api.actions.message.set;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 重启 OneBot 实现
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:46
 */
public class SetRestartAction extends Action<SetRestartAction.Params, Void> {
    /**
     * 重启 OneBot 实现
     * @author wzp
     * @since 2024/8/24 23:59 v0.0.6-dev
     * @param delay 要延迟的毫秒数，如果默认情况下无法重启，可以尝试设置延迟为 2000 左右
     */
    public SetRestartAction(Integer delay) {
        super.setAction(Actions.SET_RESTART);
        super.setParams(new Params(delay));
    }

    /**
     * 立即重启 OneBot 实现
     * @author wzp
     * @since 2024/8/24 23:58 v0.0.6-dev
     */
    public SetRestartAction() {
        this(0);
    }
    @Data
    @AllArgsConstructor
    public static final class Params {
        /**
         * 要延迟的毫秒数，如果默认情况下无法重启，可以尝试设置延迟为 2000 左右
         * @since 2024/8/24 23:49 v0.0.6-dev
         */
        @JSONField(defaultValue = "0")
        private Integer delay;
    }
}
