package cn.wzpmc.events.notice.user.increase;

import cn.wzpmc.events.notice.user.GroupUserModifyEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群成员增加事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupUserIncreaseEvent extends GroupUserModifyEvent<GroupUserIncreaseSubType> {
}
