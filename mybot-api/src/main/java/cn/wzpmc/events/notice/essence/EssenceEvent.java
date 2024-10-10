package cn.wzpmc.events.notice.essence;

import cn.wzpmc.events.notice.NoticeEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 精华消息事件
 *
 * @author wzp
 * @version 1.0.3
 * @since 2024/9/16 21:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EssenceEvent extends NoticeEvent {
    /**
     * 群精华消息子类型
     *
     * @since 2024/8/1 下午11:26 v0.0.2-dev
     */
    @JSONField(name = "sub_type")
    private EssenceSubType subType;
}
