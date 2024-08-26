package cn.wzpmc.api.actions.message.can;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.Actions;
import cn.wzpmc.entities.YesNo;

/**
 * 检查是否可以发送图片
 *
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:45
 */
public class CanSendImageAction extends Action<Void, YesNo> {
    /**
     * 检查是否可以发送图片
     *
     * @author wzp
     * @since 2024/8/24 23:51 v0.0.6-dev
     */
    public CanSendImageAction() {
        super.setAction(Actions.CAN_SEND_IMAGE);
    }
}
