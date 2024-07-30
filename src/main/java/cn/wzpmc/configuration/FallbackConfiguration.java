package cn.wzpmc.configuration;

import cn.wzpmc.api.plugins.configuration.IFallbackConfiguration;
import lombok.Data;

/**
 * 当失败时报错消息配置实现
 * @author wzp
 * @since 2024/7/31 上午3:44
 * @version 0.0.1-dev
 */
@Data
public class FallbackConfiguration implements IFallbackConfiguration {
    /**
     * 当指令执行失败时
     * @since 2024/7/31 上午3:44 v0.0.1-dev
     */
    private String command;
    /**
     * 当出现未捕获的异常时
     * @since 2024/7/31 上午3:44 v0.0.1-dev
     */
    private String errorUncaught;
}
