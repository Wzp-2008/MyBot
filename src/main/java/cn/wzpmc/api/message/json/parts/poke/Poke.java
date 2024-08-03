package cn.wzpmc.api.message.json.parts.poke;

import cn.wzpmc.api.message.json.JsonMessagePart;
import cn.wzpmc.api.message.json.parts.PartType;
import com.alibaba.fastjson2.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 戳一戳
 * @author MoYiJiangNan
 * @version 0.0.3-dev
 * @since 2024/8/2 下午11:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poke implements JsonMessagePart {
    private PokeType pokeType;

    @Override
    public PartType getPartType() {
        return PartType.POKE;
    }

    public JSONObject getData() {
        return new JSONObject()
                .fluentPut("type", this.pokeType.type)
                .fluentPut("id", this.pokeType.id)
                .fluentPut("name", this.pokeType.name);
    }
}
