package cn.wzpmc.message;

/**
 * 消息对象接口
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:33
 */
public interface MessageComponent {
    /**
     * 将其转换为发送的文本
     *
     * @return 消息文本
     * @author wzp
     * @since 2024/7/31 上午2:41 v0.0.1-dev
     */
    String toMessageString();
}
