package cn.wzpmc.mybot.entities.groupfiles;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode
@AllArgsConstructor
public class GroupFileList {
    @JSONField(name="files")
    private ArrayList<GroupFileObject> files;
    @JSONField(name="folders")
    private ArrayList<GroupFolderObject> folders;
}
