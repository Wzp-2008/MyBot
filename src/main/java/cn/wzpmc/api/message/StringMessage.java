package cn.wzpmc.api.message;

import lombok.RequiredArgsConstructor;

/**
 * 纯文本消息
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:34
 */
@RequiredArgsConstructor
public class StringMessage implements MessageComponent{
    private final String message;
    @Override
    public String toMessageString() {
        return this.message;
    }

    /**
     * 构建纯文本消息
     * @author wzp
     * @since 2024/7/31 上午2:41 v0.0.1-dev
     * @param message 消息文本
     * @return 文本消息对象
     */
    public static StringMessage text(String message){
        return new StringMessage(message);
    }
}
