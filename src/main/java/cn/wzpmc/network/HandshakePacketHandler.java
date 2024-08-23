package cn.wzpmc.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.concurrent.CompletableFuture;

/**
 * 握手包处理器
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午3:53
 */
@Log4j2
@RequiredArgsConstructor
public class HandshakePacketHandler extends SimpleChannelInboundHandler<FullHttpResponse> {
    private final WebSocketClientHandshaker handshaker;
    @Getter
    private final CompletableFuture<Boolean> handshakeFuture = new CompletableFuture<>();

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.debug("开始WebSocket握手");
        this.handshaker.handshake(ctx.channel());
        log.debug("发送握手包");
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpResponse fullHttpResponse) {
        if (!handshaker.isHandshakeComplete()) {
            handshaker.finishHandshake(channelHandlerContext.channel(), fullHttpResponse);
            this.handshakeFuture.complete(true);
            log.debug("握手成功");
            log.info("连接服务器成功！");
        }
    }
}
