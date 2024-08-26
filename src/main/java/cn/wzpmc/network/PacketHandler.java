package cn.wzpmc.network;

import cn.wzpmc.api.Action;
import cn.wzpmc.api.ActionResponse;
import cn.wzpmc.entities.api.ApiResponseRequired;
import cn.wzpmc.events.Event;
import cn.wzpmc.user.IBot;
import cn.wzpmc.utils.json.action.ActionReader;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * websocket包处理器
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午12:14
 */
@Log4j2
@RequiredArgsConstructor
public class PacketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    private final IBot bot;
    private final ExecutorService threadPool = Executors.newFixedThreadPool(4);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame webSocketFrame) {
        String text = webSocketFrame.text();
        // System.out.println(text);
        if (!JSON.isValidObject(text)) {
            log.warn("收到了无法处理的WebSocket数据包：{}", text);
            return;
        }
        JSONObject jsonObject = JSON.parseObject(text);
        if (jsonObject.containsKey("echo")) {
            handleApiEcho(text);
            return;
        }
        handleEvent(text);
    }

    /**
     * 处理事件
     *
     * @param text 事件json文本
     * @author wzp
     * @since 2024/8/23 21:47 v0.0.5-dev
     */
    private void handleEvent(String text) {
        Event event = JSON.parseObject(text, Event.class);
        threadPool.submit(() -> {
            try {
                this.bot.triggerEvent(event);
            } catch (InvocationTargetException | IllegalAccessException e) {
                log.throwing(e);
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * 处理api回调
     *
     * @param dataString 返回json文本
     * @param <REQUEST>  请求类型
     * @param <RESPONSE> 返回类型
     * @author wzp
     * @since 2024/8/23 21:48 v0.0.5-dev
     */
    private <REQUEST, RESPONSE> void handleApiEcho(String dataString) {
        //noinspection unchecked
        ActionResponse<RESPONSE> actionResponse = JSON.parseObject(dataString, ActionResponse.class);
        UUID echo = actionResponse.getEcho();
        //noinspection unchecked
        ApiResponseRequired<REQUEST, RESPONSE> apiResponseRequired = (ApiResponseRequired<REQUEST, RESPONSE>) ActionReader.tasks.get(echo);
        if (apiResponseRequired == null) {
            log.warn("收到了错误的请求返回：{}", echo);
            return;
        }
        ActionReader.tasks.remove(echo);
        apiResponseRequired.getFuture().complete(actionResponse);
    }

    /**
     * 注册返回回调
     *
     * @param echo            回调ID
     * @param responsePromise 返回Promise
     * @param request         请求体
     * @param <REQUEST>       请求体类型
     * @param <RESPONSE>      返回类型
     * @author wzp
     * @since 2024/8/23 21:48 v0.0.5-dev
     */
    public <REQUEST, RESPONSE> void registerResponse(UUID echo, CompletableFuture<ActionResponse<RESPONSE>> responsePromise, Action<REQUEST, RESPONSE> request) {
        ActionReader.tasks.put(echo, new ApiResponseRequired<>(responsePromise, request));
    }
}
