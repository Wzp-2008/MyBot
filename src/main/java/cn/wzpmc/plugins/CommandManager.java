package cn.wzpmc.plugins;

import cn.wzpmc.api.commands.BrigadierCommand;
import cn.wzpmc.api.commands.RawCommand;
import cn.wzpmc.api.message.StringMessage;
import cn.wzpmc.api.plugins.ICommandManager;
import cn.wzpmc.api.user.CommandSender;
import cn.wzpmc.api.user.IBot;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.ParseResults;
import com.mojang.brigadier.context.ParsedCommandNode;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestion;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.tree.LiteralCommandNode;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.jline.reader.*;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 指令管理器实现类
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午3:13
 */
@Log4j2
public class CommandManager implements ICommandManager, Completer, Highlighter {
    @Getter
    private final CommandDispatcher<CommandSender> dispatcher = new CommandDispatcher<>();
    @Getter
    private final ConcurrentHashMap<String, RawCommand> rawCommands = new ConcurrentHashMap<>();
    private static final int[] COLORS = {AttributedStyle.CYAN, AttributedStyle.YELLOW, AttributedStyle.GREEN, AttributedStyle.MAGENTA, AttributedStyle.BLUE};
    private final IBot bot;
    public CommandManager(IBot bot) {
        this.bot = bot;
    }
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
    @ToString
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
                if (e.getType().equals(CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownCommand())) {
                    sender.sendMessage(StringMessage.text(this.bot.getConfiguration().getFallback().getCommand()));
                    return false;
                }
                sender.sendMessage(StringMessage.text(e.getLocalizedMessage()));
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
     * @param cursor 当前光标位置
     * @return 所有被补全的指令
     */
    @SneakyThrows
    public List<String> tabComplete(CommandSender sender, String rawCommandLine, int cursor){
        CommandPart commandPart = new CommandPart(rawCommandLine);
        List<String> result = new ArrayList<>();
        if (rawCommands.containsKey(commandPart.name)) {
            result.addAll(rawCommands.get(commandPart.name).onTabComplete(sender, commandPart.args));
        }
        for (Map.Entry<String, RawCommand> stringRawCommandEntry : rawCommands.entrySet()) {
            String key = stringRawCommandEntry.getKey();
            if (key.contains(commandPart.name)){
                result.add(key);
            }
        }
        Suggestions suggestions = dispatcher.getCompletionSuggestions(dispatcher.parse(rawCommandLine, sender), cursor).get();
        result.addAll(suggestions.getList().stream().map(Suggestion::getText).collect(Collectors.toList()));
        return result;
    }

    @Override
    public void complete(LineReader lineReader, ParsedLine parsedLine, List<Candidate> list) {
        String line = parsedLine.line();
        int cursor = parsedLine.cursor();
        list.addAll(this.tabComplete(this.bot, line, cursor).stream().map(Candidate::new).collect(Collectors.toList()));
    }

    @Override
    public AttributedString highlight(LineReader lineReader, String s) {
        final AttributedStringBuilder builder = new AttributedStringBuilder();
        String[] strings = s.split(" ");
        String commandName = strings[0];
        if (rawCommands.containsKey(commandName)){
            builder.append(commandName, AttributedStyle.DEFAULT.foreground(AttributedStyle.GREEN)).append(' ');
            for (int i = 1; i < strings.length; i++) {
                builder.append(strings[i]).append(' ');
            }
        }else {
            final ParseResults<CommandSender> results = this.dispatcher.parse(s, this.bot);
            int pos = 0;
            if (s.startsWith("/")) {
                builder.append("/", AttributedStyle.DEFAULT);
                pos = 1;
            }
            int component = -1;
            for (final ParsedCommandNode<CommandSender> node : results.getContext().getLastChild().getNodes()) {
                if (node.getRange().getStart() >= s.length()) {
                    break;
                }
                final int start = node.getRange().getStart();
                final int end = Math.min(node.getRange().getEnd(), s.length());
                builder.append(s.substring(pos, start), AttributedStyle.DEFAULT);
                if (node.getNode() instanceof LiteralCommandNode) {
                    builder.append(s.substring(start, end), AttributedStyle.DEFAULT);
                } else {
                    if (++component >= COLORS.length) {
                        component = 0;
                    }
                    builder.append(s.substring(start, end), AttributedStyle.DEFAULT.foreground(COLORS[component]));
                }
                pos = end;
            }
            if (pos < s.length()) {
                builder.append((s.substring(pos)), AttributedStyle.DEFAULT.foreground(AttributedStyle.RED));
            }
        }
        return builder.toAttributedString();
    }

    @Override
    public void setErrorPattern(Pattern pattern) {}

    @Override
    public void setErrorIndex(int i) {}
}
