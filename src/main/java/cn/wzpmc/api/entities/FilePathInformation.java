package cn.wzpmc.api.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wzp
 * @version 0.0.6-dev
 * @since 2024/8/24 19:50
 */
@Data
@AllArgsConstructor
public class FilePathInformation {
    /**
     * 转换后的语音文件路径，如 /home/somebody/cqhttp/data/record/0B38145AA44505000B38145AA4450500.mp3
     * @since 2024/8/24 23:35 v0.0.6-dev
     */
    private String file;
}
