package cn.wzpmc.entities.api;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.ActionResponse;
import lombok.Data;

import java.util.concurrent.CompletableFuture;

/**
 * api返回需求
 * @author wzp
 * @since 2024/8/16 22:08
 * @version 0.0.5-dev
 */
@Data
public class ApiResponseRequired<REQUEST, RESPONSE> {
    /**
     * 请求返回Promise
     * @since 2024/8/23 21:46 v0.0.5-dev
     */
    private final CompletableFuture<ActionResponse<RESPONSE>> future;
    /**
     * 请求体
     * @since 2024/8/23 21:47 v0.0.5-dev
     */
    private final Action<REQUEST, RESPONSE> request;
}