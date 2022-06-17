package cn.wzpmc.mybot.entities.infos;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.0
 */
@AllArgsConstructor
@Data
public class ImageInfo {
    private Integer size;
    private String filename;
    private String url;
}
