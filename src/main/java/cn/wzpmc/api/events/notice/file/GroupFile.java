package cn.wzpmc.api.events.notice.file;

import lombok.Data;

/**
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午9:23
 */
@Data
public class GroupFile {
    /**
     * 文件ID
     * @since 2024/8/1 下午9:24 v0.0.2-dev
     */
    private String id;
    /**
     * 文件名
     * @since 2024/8/1 下午9:24 v0.0.2-dev
     */
    private String name;
    /**
     * 文件大小
     * @since 2024/8/1 下午9:24 v0.0.2-dev
     */
    private Long size;
    /**
     * BUSID（具体见<a href="https://github.com/botuniverse/onebot-11/blob/master/event/notice.md">OneBot文档</a>）
     * @since 2024/8/1 下午9:24 v0.0.2-dev
     */
    private Long busid;
}
