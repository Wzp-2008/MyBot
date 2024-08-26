package cn.wzpmc.console;

import cn.wzpmc.entities.user.bot.MyBot;
import cn.wzpmc.network.WebSocketConnectionHandler;
import cn.wzpmc.plugins.CommandManager;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minecrell.terminalconsole.SimpleTerminalConsole;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;

/**
 * 主控制台
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/7/31 下午9:47
 */
@Log4j2
@RequiredArgsConstructor
public class MyBotConsole extends SimpleTerminalConsole {
    private final MyBot bot;
    private final CommandManager commandManager;
    private final WebSocketConnectionHandler webSocketConnectionHandler;
    @Getter
    private boolean running = true;

    public MyBotConsole(MyBot bot, WebSocketConnectionHandler webSocketConnectionHandler) {
        this.bot = bot;
        this.commandManager = bot.getCommandManager();
        this.webSocketConnectionHandler = webSocketConnectionHandler;
    }

    @Override
    protected LineReader buildReader(LineReaderBuilder builder) {
        return super.buildReader(builder.appName("MyBot").completer(commandManager).highlighter(commandManager));
    }

    @Override
    protected void runCommand(String command) {
        if (!commandManager.execute(this.bot, command)) {
            log.warn("执行指令：`{}`失败！", command);
        }
    }

    @Override
    public void shutdown() {
        this.webSocketConnectionHandler.kill();
        running = false;
    }

    @Override
    public void start() {
        this.bot.setConsole(this);
        super.start();
    }
}