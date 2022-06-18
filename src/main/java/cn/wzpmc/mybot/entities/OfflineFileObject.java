package cn.wzpmc.mybot.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:38
 */
@Data
@EqualsAndHashCode
public class OfflineFileObject {
    private String name;
    private Long size;
    private String url;
}
