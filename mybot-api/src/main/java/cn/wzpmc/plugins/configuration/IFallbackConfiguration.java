package cn.wzpmc.plugins.configuration;

/**
 * 通信验证配置
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午3:43
 */
public interface IFallbackConfiguration {
    /**
     * @return 当指令执行失败时
     * @author wzp
     * @since 2024/7/31 上午3:47 v0.0.1-dev
     */
    String getCommand();

    /**
     * @return 当出现未捕获的异常时
     * @author wzp
     * @since 2024/7/31 上午3:47 v0.0.1-dev
     */
    String getErrorUncaught();
}
