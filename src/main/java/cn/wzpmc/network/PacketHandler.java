package cn.wzpmc.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

/**
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午12:14
 */
@Log4j2
@RequiredArgsConstructor
public class PacketHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private final WebSocketClientHandshaker handshaker;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.debug("开始WebSocket握手");
        this.handshaker.handshake(ctx.channel());
        log.debug("握手完成");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame) throws Exception {
        System.out.println(webSocketFrame);
    }
}
