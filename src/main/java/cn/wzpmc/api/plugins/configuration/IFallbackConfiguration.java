package cn.wzpmc.api.plugins.configuration;

/**
 * 通信验证配置
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午3:43
 */
public interface IFallbackConfiguration {
    /**
     * @author wzp
     * @since 2024/7/31 上午3:47 v0.0.1-dev
     * @return 当指令执行失败时
     */
    String getCommand();

    /**
     * @author wzp
     * @since 2024/7/31 上午3:47 v0.0.1-dev
     * @return 当出现未捕获的异常时
     */
    String getErrorUncaught();
}
