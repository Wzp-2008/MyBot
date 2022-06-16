package cn.wzpmc.mybot.pojo.groupfiles;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class GroupFolderObject {
    @JSONField(name="group_id")
    private Long groupId;
    @JSONField(name="folder_id")
    private String folderId;
    @JSONField(name="folder_name")
    private String folderName;
    @JSONField(name="create_time")
    private Long createTime;
    @JSONField(name="creator")
    private Long creator;
    @JSONField(name="creator_name")
    private String creatorName;
    @JSONField(name="total_file_count")
    private Integer totalFileCount;
}
