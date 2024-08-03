package cn.wzpmc.api.message.json;

import cn.wzpmc.api.message.json.parts.PartType;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.annotation.JSONField;
import com.alibaba.fastjson2.annotation.JSONType;

/**
 * JSON消息段
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:36
 */
@JSONType(includes = {"type", "data"})
public interface JsonMessagePart {
    /**
     * 获取消息的类型
     * @author wzp
     * @since 2024/7/31 上午2:40 v0.0.1-dev
     * @return 消息类型
     */
    PartType getPartType();
    @JSONField(name = "type")
    default String getStringPartType(){
        return this.getPartType().toString().toLowerCase();
    }

    /**
     * 获取消息附带的数据
     * @author wzp
     * @since 2024/7/31 上午2:40 v0.0.1-dev
     * @return 数据
     */
    JSONObject getData();

    /**
     * 获取当纯文本界面的代替文本
     * @author wzp
     * @since 2024/7/31 上午2:45 v0.0.1-dev
     * @return 文本
     */
    default String getTextDisplay(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getPartType())
                .append('(')
                .append('\n');
        JSONObject data = this.getData();
        data.forEach((key, value) -> stringBuilder.append('\t').append(key).append('=').append(value).append(',').append('\n'));
        int length = stringBuilder.length();
        stringBuilder.delete(length - 2, length - 1);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    /**
     * 获取消息段的CQ码
     * @author wzp
     * @since 2024/8/2 下午9:32 v0.0.3-dev
     * @return CQ码
     */
    default String getCQCode(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[CQ:").append(this.getPartType().toString().toLowerCase()).append(',');
        JSONObject data = getData();
        data.forEach((key, value) -> stringBuilder.append(key).append('=').append(value).append(','));
        int lastSplit = stringBuilder.length() - 1;
        stringBuilder.deleteCharAt(lastSplit);
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
