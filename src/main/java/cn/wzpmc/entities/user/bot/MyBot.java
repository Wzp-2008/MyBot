package cn.wzpmc.entities.user.bot;

import cn.wzpmc.api.message.MessageComponent;
import cn.wzpmc.api.message.StringMessage;
import cn.wzpmc.api.message.json.JsonMessage;
import cn.wzpmc.api.user.IBot;
import cn.wzpmc.api.user.permission.Permissions;
import cn.wzpmc.configuration.Configuration;
import cn.wzpmc.console.MyBotConsole;
import cn.wzpmc.plugins.CommandManager;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * 机器人实现类
 * @author wzp
 * @since 2024/7/30 下午11:46
 * @version 0.0.1-dev
 */
@Log4j2
@Getter
public class MyBot extends IBot {
    private final Configuration configuration;
    @Setter
    private Long id;
    @Setter
    private String name;
    private final CommandManager commandManager = new CommandManager(this);
    @Setter
    private MyBotConsole console = null;
    public MyBot(Configuration configuration){
        this.configuration = configuration;
        this.permissions = Permissions.ADMIN;
    }

    @Override
    public void sendMessage(MessageComponent messageComponent) {
        if (messageComponent instanceof StringMessage){
            log.info(messageComponent.toMessageString());
        }
        if (messageComponent instanceof JsonMessage){
            log.info(((JsonMessage) messageComponent).toTextDisplay());
        }
    }

    @Override
    public void stop() {
        if (this.console != null) {
            this.console.shutdown();
        }
    }
}
