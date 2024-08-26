package cn.wzpmc.events.notice;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 好友添加事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FriendAddEvent extends NoticeEvent {
    /**
     * 用户ID
     *
     * @since 2024/8/1 下午11:13 v0.0.2-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
}
