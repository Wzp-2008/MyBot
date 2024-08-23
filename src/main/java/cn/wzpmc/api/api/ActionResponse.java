package cn.wzpmc.api.api;

import lombok.Data;

import java.util.UUID;

/**
 * 请求操作返回
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/16 17:43
 */
@Data
public class ActionResponse<RESPONSE> {
    /**
     * 请求返回消息
     * @since 2024/8/23 21:30 v0.0.5-dev
     */
    private final String status;
    /**
     * 请求返回码
     * @since 2024/8/23 21:30 v0.0.5-dev
     */
    private final short retcode;
    /**
     * 请求返回数据
     * @since 2024/8/23 21:31 v0.0.5-dev
     */
    private final RESPONSE data;
    /**
     * 请求返回回调
     * @since 2024/8/23 21:31 v0.0.5-dev
     */
    private final UUID echo;
}
