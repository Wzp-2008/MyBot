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
@EqualsAndHashCode
@AllArgsConstructor
@Data
public class GroupFileObject {
    @JSONField(name="group_id")
    private Integer groupId;
    @JSONField(name="file_id")
    private String fileId;
    @JSONField(name="file_name")
    private String fileName;
    @JSONField(name="busid")
    private Long busid;
    @JSONField(name="file_size")
    private Long fileSize;
    @JSONField(name="upload_time")
    private Long uploadTime;
    @JSONField(name="dead_time")
    private Long deadTime;
    @JSONField(name="modify_time")
    private Long modifyTime;
    @JSONField(name="download_times")
    private Integer downloadTimes;
    @JSONField(name="uploader")
    private Long uploader;
    @JSONField(name="uploader_name")
    private String uploaderName;
    public GroupFileObject(String id,String name,Long size,Long busid){
        this.fileId = id;
        this.fileName = name;
        this.fileSize = size;
        this.busid = busid;
    }
}
