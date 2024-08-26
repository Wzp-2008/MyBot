package cn.wzpmc.message.json.parts.node;

import cn.wzpmc.message.json.JsonMessagePart;
import cn.wzpmc.message.json.parts.PartType;
import lombok.NoArgsConstructor;

/**
 * 合并转发节点
 *
 * @author wzp
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:39
 */
@NoArgsConstructor
public abstract class Node implements JsonMessagePart {
    @Override
    public PartType getPartType() {
        return PartType.NODE;
    }
}
