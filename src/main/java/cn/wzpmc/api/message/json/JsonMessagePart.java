package cn.wzpmc.api.message.json;

import java.util.Map;

/**
 * JSON消息段
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:36
 */
public interface JsonMessagePart {
    /**
     * 获取消息的类型
     * @author wzp
     * @since 2024/7/31 上午2:40 v0.0.1-dev
     * @return 消息类型字符串
     */
    String getType();

    /**
     * 获取消息附带的数据
     * @author wzp
     * @since 2024/7/31 上午2:40 v0.0.1-dev
     * @return 数据
     */
    Map<String, String> getData();

    /**
     * 获取当纯文本界面的代替文本
     * @author wzp
     * @since 2024/7/31 上午2:45 v0.0.1-dev
     * @return 文本
     */
    default String getTextDisplay(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.getType())
                .append('(')
                .append('\n');
        Map<String, String> data = this.getData();
        data.forEach((key, value) -> stringBuilder.append('\t').append(key).append('=').append(value).append(',').append('\n'));
        int length = stringBuilder.length();
        stringBuilder.delete(length - 2, length - 1);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }
}
