package cn.wzpmc.mybot.entities.events.other;

import cn.wzpmc.mybot.entities.OfflineFileObject;
import cn.wzpmc.mybot.entities.events.Event;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/6/18 10:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GetOfflineFileEvent extends Event {
    private Long userId;
    private OfflineFileObject file;

    public GetOfflineFileEvent(JSONObject data) {
        super("GetOfflineFileEvent", data);
        this.userId = data.getLong("user_id");
        this.file = data.getJSONObject("file").toJavaObject(OfflineFileObject.class);
    }
}
