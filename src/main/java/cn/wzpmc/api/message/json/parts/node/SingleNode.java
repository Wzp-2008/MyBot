package cn.wzpmc.api.message.json.parts.node;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 合并转发节点
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:41
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class SingleNode extends Node{
    /**
     * 转发的消息 ID
     * @since 2024/8/3 下午6:10 v0.0.3-dev
     */
    private Long id;
    @Override
    public JSONObject getData() {
        return JSONObject.of("id", this.id);
    }
}
