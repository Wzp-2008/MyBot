package cn.wzpmc.message.json.parts.contact;

import cn.wzpmc.message.json.JsonMessagePart;
import cn.wzpmc.message.json.parts.PartType;
import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 推荐好友/群
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:21
 */
@Data
@NoArgsConstructor
public class Contact implements JsonMessagePart {
    /**
     * 推荐类型
     *
     * @since 2024/8/3 下午5:57 v0.0.3-dev
     */
    private ContactType type;
    /**
     * 被推荐人的 QQ 号
     *
     * @since 2024/8/3 下午5:57 v0.0.3-dev
     */
    private Long id;

    @Override
    public PartType getPartType() {
        return PartType.CONTACT;
    }

    @Override
    public JSONObject getData() {
        return JSONObject.of("type", this.type.name().toLowerCase(), "id", this.id);
    }
}
