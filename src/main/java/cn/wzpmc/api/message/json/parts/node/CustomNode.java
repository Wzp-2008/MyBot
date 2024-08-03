package cn.wzpmc.api.message.json.parts.node;

import cn.wzpmc.api.message.MessageComponent;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 合并转发自定义节点
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CustomNode extends Node {
    /**
     * 发送者 QQ 号
     * @since 2024/8/3 下午6:10 v0.0.3-dev
     */
    @JSONField(name = "user_id")
    private Long userId;
    /**
     * 发送者昵称
     * @since 2024/8/3 下午6:11 v0.0.3-dev
     */
    private String nickname;
    /**
     * 消息内容
     * @since 2024/8/3 下午6:11 v0.0.3-dev
     */
    private MessageComponent content;
    @Override
    public JSONObject getData() {
        return JSONObject.of("user_id", userId, "nickname", nickname, "content", content);
    }
}
