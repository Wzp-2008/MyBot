package cn.wzpmc.mybot.pojo;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 * @date 2022/5/8
 */
@Data
@AllArgsConstructor
public class FileObject {
    @JSONField(name="id")
    private String id;
    @JSONField(name="name")
    private String name;
    @JSONField(name="size")
    private Long size;
    @JSONField(name="busid")
    private Long busid;
}
