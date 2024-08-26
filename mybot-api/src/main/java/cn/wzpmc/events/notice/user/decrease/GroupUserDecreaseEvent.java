package cn.wzpmc.events.notice.user.decrease;

import cn.wzpmc.events.notice.user.GroupUserModifyEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群成员减少事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupUserDecreaseEvent extends GroupUserModifyEvent<GroupUserDecreaseSubType> {
}
