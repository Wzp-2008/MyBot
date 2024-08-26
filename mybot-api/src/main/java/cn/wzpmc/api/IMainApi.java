package cn.wzpmc.api;

/**
 * 主Api接口
 *
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/16 17:31
 */
public interface IMainApi {
    /**
     * 进行请求操作
     *
     * @param packet     请求包
     * @param <REQUEST>  请求类型
     * @param <RESPONSE> 返回类型
     * @return 请求返回包
     * @throws InterruptedException 请求过程中出现Ctrl+C时抛出
     * @author wzp
     * @since 2024/8/23 21:32 v0.0.5-dev
     */
    <REQUEST, RESPONSE> ActionResponse<RESPONSE> doApiCall(Action<REQUEST, RESPONSE> packet) throws InterruptedException;
}
