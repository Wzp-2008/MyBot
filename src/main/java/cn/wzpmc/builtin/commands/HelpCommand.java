package cn.wzpmc.builtin.commands;

import cn.wzpmc.commands.BrigadierCommand;
import cn.wzpmc.commands.RawCommand;
import cn.wzpmc.entities.user.bot.MyBot;
import cn.wzpmc.message.StringMessage;
import cn.wzpmc.plugins.CommandManager;
import cn.wzpmc.user.CommandSender;
import cn.wzpmc.user.IBot;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import com.mojang.brigadier.tree.RootCommandNode;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 15:07
 */
public class HelpCommand implements BrigadierCommand {
    private final CommandManager commandManager;

    public HelpCommand() {
        IBot instance = MyBot.getInstance();
        this.commandManager = (CommandManager) instance.getCommandManager();
    }

    private static void handlerNode(Collection<CommandNode<CommandSender>> node, int tabCount, StringBuilder builder) {
        for (CommandNode<CommandSender> commandSenderCommandNode : node) {
            builder.append("\t".repeat(Math.max(0, tabCount)));
            builder.append(commandSenderCommandNode.getUsageText());
            builder.append('\n');
            handlerNode(commandSenderCommandNode.getChildren(), tabCount + 1, builder);
        }
    }

    private Collection<CommandNode<CommandSender>> getRootCommandNode() {
        CommandDispatcher<CommandSender> dispatcher = this.commandManager.getDispatcher();
        RootCommandNode<CommandSender> root = dispatcher.getRoot();
        return root.getChildren();
    }

    @Override
    public LiteralArgumentBuilder<CommandSender> getCommandNode() {
        return LiteralArgumentBuilder.<CommandSender>literal("help").
                executes(e -> {
                    Collection<CommandNode<CommandSender>> children = getRootCommandNode();
                    CommandSender source = e.getSource();
                    for (CommandNode<CommandSender> child : children) {
                        StringBuilder builder = new StringBuilder();
                        builder.append('/');
                        builder.append(child.getUsageText());
                        builder.append('\n');
                        handlerNode(child.getChildren(), 1, builder);
                        builder.deleteCharAt(builder.lastIndexOf("\n"));
                        source.sendMessage(StringMessage.text(builder.toString()));
                    }
                    ConcurrentHashMap<String, RawCommand> rawCommands = this.commandManager.getRawCommands();
                    for (Map.Entry<String, RawCommand> stringRawCommandEntry : rawCommands.entrySet()) {
                        source.sendMessage(StringMessage.text("/" + stringRawCommandEntry.getKey()));
                    }
                    return 0;
                }).
                then(RequiredArgumentBuilder.<CommandSender, String>
                                argument("root", StringArgumentType.word()).
                        executes(e -> {
                            String root = e.getArgument("root", String.class);
                            Collection<CommandNode<CommandSender>> children = getRootCommandNode();
                            CommandSender source = e.getSource();
                            for (CommandNode<CommandSender> child : children) {
                                if (!child.getName().equals(root)) {
                                    continue;
                                }
                                StringBuilder builder = new StringBuilder();
                                builder.append('/');
                                builder.append(child.getUsageText());
                                builder.append('\n');
                                handlerNode(child.getChildren(), 1, builder);
                                builder.deleteCharAt(builder.lastIndexOf("\n"));
                                source.sendMessage(StringMessage.text(builder.toString()));
                            }
                            ConcurrentHashMap<String, RawCommand> rawCommands = this.commandManager.getRawCommands();
                            for (Map.Entry<String, RawCommand> stringRawCommandEntry : rawCommands.entrySet()) {
                                source.sendMessage(StringMessage.text("/" + stringRawCommandEntry.getKey()));
                            }
                            return 0;
                        })
                );
    }
}
