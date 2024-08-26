package cn.wzpmc.events.notice;

import cn.wzpmc.events.Event;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通知基事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NoticeEvent extends Event {
    /**
     * 通知类型
     *
     * @since 2024/8/1 下午11:12 v0.0.2-dev
     */
    @JSONField(name = "notice_type")
    private NoticeType noticeType;
}
