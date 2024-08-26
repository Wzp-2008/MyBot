package cn.wzpmc.plugins.api;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.ActionResponse;
import cn.wzpmc.api.IMainApi;
import cn.wzpmc.network.WebSocketConnectionHandler;
import cn.wzpmc.user.IBot;
import lombok.RequiredArgsConstructor;

/**
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/16 17:32
 */
@RequiredArgsConstructor
public class MainApi implements IMainApi {
    private final IBot bot;
    private final WebSocketConnectionHandler handler;

    @Override
    public <REQUEST, RESPONSE> ActionResponse<RESPONSE> doApiCall(Action<REQUEST, RESPONSE> packet) throws InterruptedException {
        return handler.sendRequest(packet);
    }
}
