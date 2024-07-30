package cn.wzpmc.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.log4j.Log4j2;

/**
 * websocket包处理器
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午12:14
 */
@Log4j2
public class PacketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame webSocketFrame) {
        log.info(webSocketFrame.text());
    }
}
