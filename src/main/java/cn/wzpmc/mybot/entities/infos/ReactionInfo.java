package cn.wzpmc.mybot.entities.infos;

import com.alibaba.fastjson2.annotation.JSONCreator;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 1.0.0
 * Created On 2022/7/2 19:54
 */
@Data
@EqualsAndHashCode
public class ReactionInfo {
    @JSONField(name = "emoji_id")
    private String emojiId;
    @JSONField(name = "emoji_index")
    private Integer emojiIndex;
    @JSONField(name = "emoji_type")
    private Integer emojiType;
    @JSONField(name = "emoji_name")
    private String emojiName;
    @JSONField(name = "count")
    private Integer count;
    @JSONField(name = "clicked")
    private Boolean clicked;

    @JSONCreator
    public ReactionInfo(@JSONField(name = "emoji_id")
                        String emojiId,
                        @JSONField(name = "emoji_index")
                        Integer emojiIndex,
                        @JSONField(name = "emoji_type")
                        Integer emojiType,
                        @JSONField(name = "emoji_name")
                        String emojiName,
                        @JSONField(name = "count")
                        Integer count,
                        @JSONField(name = "clicked")
                        Boolean clicked) {
        this.emojiId = emojiId;
        this.emojiIndex = emojiIndex;
        this.emojiType = emojiType;
        this.emojiName = emojiName;
        this.count = count;
        this.clicked = clicked;
    }
}
