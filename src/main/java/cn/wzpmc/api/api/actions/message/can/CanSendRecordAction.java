package cn.wzpmc.api.api.actions.message.can;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.Actions;
import cn.wzpmc.api.entities.YesNo;

/**
 * 检查是否可以发送语音
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 20:45
 */
public class CanSendRecordAction extends Action<Void, YesNo> {
    /**
     * 检查是否可以发送语音
     * @author wzp
     * @since 2024/8/24 23:51 v0.0.6-dev
     */
    public CanSendRecordAction(){
        super.setAction(Actions.CAN_SEND_RECORD);
    }
}
