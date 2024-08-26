package cn.wzpmc.events.notice.file;

import cn.wzpmc.events.notice.NoticeEvent;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 群文件上传事件
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupFileUploadedEvent extends NoticeEvent {
    /**
     * 群组ID
     *
     * @since 2024/8/1 下午9:25 v0.0.2-dev
     */
    @JSONField(name = "group_id")
    private Long groupId;
    /**
     * 用户ID
     *
     * @since 2024/8/1 下午9:25 v0.0.2-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
    /**
     * 文件详细消息
     *
     * @since 2024/8/1 下午9:25 v0.0.2-dev
     */
    @JSONField(name = "group_file")
    private GroupFile groupFile;
}
