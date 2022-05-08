package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/8
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class GroupFileSystemInfo {
    @JSONField(name="file_count")
    private Integer fileCount;
    @JSONField(name="limit_count")
    private Integer limitCount;
    @JSONField(name="used_space")
    private Long usedSpace;
    @JSONField(name="total_space")
    private Long totalSpace;
}
