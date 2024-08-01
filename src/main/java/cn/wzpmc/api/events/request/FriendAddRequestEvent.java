package cn.wzpmc.api.events.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 加好友事件
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:13
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FriendAddRequestEvent extends RequestEvent {
}
