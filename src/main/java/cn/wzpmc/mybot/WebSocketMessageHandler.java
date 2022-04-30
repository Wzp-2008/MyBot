package cn.wzpmc.mybot;

import cn.wzpmc.mybot.Event.GroupMessageEvent;
import cn.wzpmc.mybot.Event.PrivateMessageEvent;
import cn.wzpmc.mybot.interfaces.CommandExecutor;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;
import cn.wzpmc.mybot.pojo.Command;
import cn.wzpmc.mybot.pojo.GroupMessage;
import cn.wzpmc.mybot.utils.BytesUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 33572
 */
@Slf4j
public class WebSocketMessageHandler extends SimpleChannelInboundHandler<Object> {
    private final WebSocketClientHandshaker handshaker;
    private boolean unclosed;
    private StringBuffer buffer;
    public  WebSocketMessageHandler(WebSocketClientHandshaker handshaker) {
        this.handshaker = handshaker;
    }
    @Override
    public void channelActive(ChannelHandlerContext channelHandlerContext){
        log.info("开始建立websocket连接");
        Channel channel = channelHandlerContext.channel();
        this.handshaker.handshake(channel);
        log.info("成功建立websocket连接");
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext context,Throwable cause){
        log.error(cause.getLocalizedMessage());
        cause.printStackTrace();
    }
    @Override
    public void handlerRemoved(ChannelHandlerContext context){
        log.error("和websocket服务器断开连接");
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext context){
        context.flush();
    }

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
            }catch (JSONException e){
                unclosed = true;
                buffer = new StringBuffer();
                buffer.append(fullJson);
                return;
            }
            String type = data.getString("post_type");
            String messageType = data.getString("message_type");
            String group = "group";
            String privateMessage = "private";
            String message = "message";
            Bot bot = Main.bot;
            Properties config = Main.getConfig();
            ConcurrentHashMap<MyBotPlugin, ConcurrentHashMap<Class<?>, Method>> events = bot.getEvents();
            ConcurrentHashMap.KeySetView<MyBotPlugin, ConcurrentHashMap<Class<?>, Method>> myBotPlugins = events.keySet();
            if(message.equals(type)){
                if(group.equals(messageType)){
                    GroupMessageEvent groupMessageEvent = new GroupMessageEvent(data,bot);
                    GroupMessage gMessage = groupMessageEvent.getMessage();
                    String content = gMessage.getContent();
                    String s = bot.at.toString() + " /";
                    boolean matches = content.contains(s);
                    if(matches){
                        Command command = new Command(content.replace(s, ""),gMessage);
                        Map<Command, CommandExecutor> commands = bot.getCommands();
                        CommandExecutor commandExecutor = commands.get(command);
                        if (commandExecutor != null) {
                            boolean execute = commandExecutor.execute(command);
                            if(!execute){
                                gMessage.reply(config.getProperty("failed_run_message"));
                            }
                        }else{
                            gMessage.reply(config.getProperty("not_found_command_message"));
                        }
                    }else{
                        for (MyBotPlugin myBotPlugin : myBotPlugins) {
                            ConcurrentHashMap<Class<?>, Method> eventWithMethod = events.get(myBotPlugin);
                            Method method = eventWithMethod.get(GroupMessageEvent.class);
                            method.invoke(null,groupMessageEvent);
                        }
                    }
                }else if(privateMessage.equals(messageType)){
                    PrivateMessageEvent event = new PrivateMessageEvent(data,bot);
                    for (MyBotPlugin myBotPlugin : myBotPlugins) {
                        ConcurrentHashMap<Class<?>, Method> eventWithMethod = events.get(myBotPlugin);
                        Method method = eventWithMethod.get(PrivateMessageEvent.class);
                        method.invoke(null,event);
                    }
                }
            }
            log.info("recv JsonData = {}",data);
        }
    }

    public WebSocketClientHandshaker getHandshaker() {
        return handshaker;
    }
}
