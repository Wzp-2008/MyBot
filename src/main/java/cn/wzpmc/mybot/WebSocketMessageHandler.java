package cn.wzpmc.mybot;

import cn.wzpmc.mybot.Event.*;
import cn.wzpmc.mybot.interfaces.CommandExecutor;
import cn.wzpmc.mybot.pojo.Command;
import cn.wzpmc.mybot.pojo.GroupMessage;
import cn.wzpmc.mybot.utils.BytesUtils;
import cn.wzpmc.mybot.utils.EventUtils;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;

import static cn.wzpmc.mybot.constants.StringConstants.*;
import static cn.wzpmc.mybot.Main.bot;

/**
 * @author 33572
 * @date 2022/03/29 20:54
 * @version 1.0.0
 * websocket消息处理类
 */
public class WebSocketMessageHandler extends SimpleChannelInboundHandler<Object> {
    private final WebSocketClientHandshaker handshaker;
    private boolean unclosed;
    private StringBuffer buffer;
    public  WebSocketMessageHandler(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }
    private static final Logger log = LoggerFactory.getLogger("MyBot-Thread-Network");

    /**
     * 当websocket连接激活时调用
     * @param channelHandlerContext 通道
     */
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext){
        log.info("开始建立websocket连接");
        //获取连接通道
        Channel channel = channelHandlerContext.channel();
        //进行websocket握手
        this.handshaker.handshake(channel);
        log.info("成功建立websocket连接");
    }

    /**
     * 当websocket连接出现错误时调用
     * @param context 通道
     * @param cause 错误
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        log.error(cause.getLocalizedMessage());
        cause.printStackTrace();
    }

    /**
     * 当websocket连接断开时调用
     * @param context 通道
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext context){
        log.error("和websocket服务器断开连接");
    }

    /**
     * 当通道数据处理完成时调用
     * @param context 通道
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext context){
        context.flush();
    }

    /**
     * 当通道收到数据时调用
     * @param ctx 通道
     * @param msg 消息内容
     * @throws Exception 当通道出现错误时抛出
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf){
            String fullJson;
            if(unclosed){
                String m = BytesUtils.getStringFromByteBuf((ByteBuf) msg ,0);
                buffer.append(m);
                fullJson = buffer.toString();
                buffer = new StringBuffer();
                unclosed = false;
            }else{
                fullJson = BytesUtils.getStringFromByteBuf((ByteBuf) msg,4);
            }
            JSONObject data;
            try {
                data = JSON.parseObject(fullJson);
            }catch (IndexOutOfBoundsException e){
                unclosed = true;
                buffer = new StringBuffer();
                buffer.append(fullJson);
                return;
            }
            //事件类型
            String postType = data.getString("post_type");
            Properties config = Main.getConfig();
            if(MESSAGE.equals(postType)){
                //消息类型
                String messageType = data.getString("message_type");
                //群组消息
                if(GROUP.equals(messageType)){
                    //获取event
                    GroupMessageEvent groupMessageEvent = new GroupMessageEvent(data);
                    GroupMessage gMessage = groupMessageEvent.getMessage();
                    String content = gMessage.getContent();
                    String s = bot.at.toString() + " /";
                    boolean matches = content.contains(s);
                    if(matches){
                        Command command = new Command(content.replace(s, ""),gMessage);
                        log.info("用户" + gMessage.getSender().getId() + "运行了命令：" + content.replace(bot.at.toString(),""));
                        Map<Command, CommandExecutor> commands = bot.getCommands();
                        CommandExecutor commandExecutor = commands.get(command);
                        if (commandExecutor != null) {
                            boolean execute = commandExecutor.execute(command,gMessage.getSender());
                            if(!execute){
                                gMessage.reply(config.getProperty("failed_run_message"),bot);
                            }
                        }else{
                            gMessage.reply(config.getProperty("not_found_command_message"),bot);
                        }
                    }else{
                        EventUtils.runEvent(groupMessageEvent);
                    }
                }else if(PRIVATE.equals(messageType)){
                    PrivateMessageEvent event = new PrivateMessageEvent(data);
                    EventUtils.runEvent(event);
                }
            }else if(META_EVENT.equals(postType)){
                String metaEventType = data.getString("meta_event_type");
                if(LIFECYCLE.equals(metaEventType)){
                    String subType = data.getString("sub_type");
                    if(CONNECT.equals(subType)){
                        BotGetConnectEvent botGetConnectEvent = new BotGetConnectEvent(data);
                        EventUtils.runEvent(botGetConnectEvent);
                    }
                }
                if(HEARTBEAT.equals(metaEventType)){
                    ServerHeartbeatEvent event = new ServerHeartbeatEvent(data);
                    EventUtils.runEvent(event);
                }
            } else if (NOTICE.equals(postType)) {
                String noticeType = data.getString("notice_type");
                if(GROUP_UPLOAD.equals(noticeType)){
                    GroupFileUploadEvent groupFileUploadEvent = new GroupFileUploadEvent(data);
                    EventUtils.runEvent(groupFileUploadEvent);
                } else if (GROUP_ADMIN.equals(noticeType)) {
                    String subType = data.getString("sub_type");
                    if (SET.equals(subType)){
                        GroupAdminSetEvent groupAdminSetEvent = new GroupAdminSetEvent(data);
                        EventUtils.runEvent(groupAdminSetEvent);
                    }else if (UNSET.equals(subType)){
                        GroupAdminUnSetEvent groupAdminUnSetEvent = new GroupAdminUnSetEvent(data);
                        EventUtils.runEvent(groupAdminUnSetEvent);
                    }
                } else if (GROUP_DECREASE.equals(noticeType)) {
                    String subType = data.getString("sub_type");
                    if(LEAVE.equals(subType)){
                        GroupMemberDecreasesLeaveEvent groupMemberDecreasesLeaveEvent = new GroupMemberDecreasesLeaveEvent(data);
                        EventUtils.runEvent(groupMemberDecreasesLeaveEvent);
                    } else if (KICK.equals(subType)) {
                        GroupMemberDeceasesKickEvent groupMemberDeceasesKickEvent = new GroupMemberDeceasesKickEvent(data);
                        EventUtils.runEvent(groupMemberDeceasesKickEvent);
                    } else if (KICK_ME.equals(subType)) {
                        GroupMemberDeceasesKickMeEvent groupMemberDeceasesKickMeEvent = new GroupMemberDeceasesKickMeEvent(data);
                        EventUtils.runEvent(groupMemberDeceasesKickMeEvent);
                    }
                }
            }
            log.info("get JsonData = {}",data);
        }
    }
}
