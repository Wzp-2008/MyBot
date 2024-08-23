package cn.wzpmc.plugins.api;

import cn.wzpmc.api.api.Action;
import cn.wzpmc.api.api.ActionResponse;
import cn.wzpmc.api.api.IMainApi;
import cn.wzpmc.api.user.IBot;
import cn.wzpmc.network.WebSocketConnectionHandler;
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
