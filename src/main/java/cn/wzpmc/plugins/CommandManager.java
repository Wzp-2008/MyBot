package cn.wzpmc.plugins;

import cn.wzpmc.api.commands.BrigadierCommand;
import cn.wzpmc.api.commands.RawCommand;
import cn.wzpmc.api.plugins.ICommandManager;
import cn.wzpmc.api.user.CommandSender;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 指令管理器实现类
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午3:13
 */
@Log4j2
@NoArgsConstructor
public class CommandManager implements ICommandManager {
    private final CommandDispatcher<CommandSender> dispatcher = new CommandDispatcher<>();
    private final ConcurrentHashMap<String, RawCommand> rawCommands = new ConcurrentHashMap<>();
    @Override
    public void registerCommand(RawCommand rawCommand, String name) {
        if (rawCommands.containsKey(name)){
            log.error("指令{}已经被注册，注册失败！", name);
            return;
        }
        this.rawCommands.put(name, rawCommand);
    }
    @Override
    public void registerCommand(BrigadierCommand brigadierCommand){
        dispatcher.register(brigadierCommand.getCommandNode());
    }
    private static final class CommandPart {
        private final String name;
        private final List<String> args;
        public CommandPart(String rawCommandLine) {
            List<String> list = Arrays.asList(rawCommandLine.split(" "));
            this.name  = list.get(0);
            this.args = list.subList(1, list.size());
        }
    }

    /**
     * 执行指令
     * @author wzp
     * @since 2024/7/31 上午3:35 v0.0.1-dev
     * @param sender 发送者
     * @param rawCommandLine 完整命令行
     * @return 是否执行成功
     */
    public boolean execute(CommandSender sender, String rawCommandLine){
        CommandPart commandPart = new CommandPart(rawCommandLine);
        if (rawCommands.containsKey(commandPart.name)) {
            return rawCommands.get(commandPart.name).onExecute(sender, commandPart.args);
        }else {
            try {
                dispatcher.execute(rawCommandLine, sender);
            } catch (CommandSyntaxException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * tab补全的结果
     * @author wzp
     * @since 2024/7/31 上午3:36 v0.0.1-dev
     * @param sender 消息发送者
     * @param rawCommandLine 完整命令行
     * @return 所有被补全的指令
     */
    @SneakyThrows
    public List<String> tabComplete(CommandSender sender, String rawCommandLine){
        CommandPart commandPart = new CommandPart(rawCommandLine);
        List<String> result = new ArrayList<>();
        if (rawCommands.containsKey(commandPart.name)) {
            result.addAll(rawCommands.get(commandPart.name).onTabComplete(sender, commandPart.args));
        }
        Suggestions suggestions = dispatcher.getCompletionSuggestions(dispatcher.parse(rawCommandLine, sender)).get();
        result.addAll(suggestions.getList().stream().map(Suggestion::getText).collect(Collectors.toList()));
        return result;
    }
}
