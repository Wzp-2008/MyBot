package cn.wzpmc.builtin.commands;

import cn.wzpmc.api.commands.BrigadierCommand;
import cn.wzpmc.api.commands.RawCommand;
import cn.wzpmc.api.message.StringMessage;
import cn.wzpmc.api.user.CommandSender;
import cn.wzpmc.api.user.IBot;
import cn.wzpmc.entities.user.bot.MyBot;
import cn.wzpmc.plugins.CommandManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
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
    @Override
    public LiteralArgumentBuilder<CommandSender> getCommandNode() {
        return LiteralArgumentBuilder.<CommandSender>literal("help").executes(e -> {
            IBot instance = MyBot.getInstance();
            CommandManager commandManager = (CommandManager) instance.getCommandManager();
            CommandDispatcher<CommandSender> dispatcher = commandManager.getDispatcher();
            RootCommandNode<CommandSender> root = dispatcher.getRoot();
            Collection<CommandNode<CommandSender>> children = root.getChildren();
            CommandSender source = e.getSource();
            for (CommandNode<CommandSender> child : children) {
                StringBuilder builder = new StringBuilder();
                builder.append('\n');
                builder.append('/');
                builder.append(child.getUsageText());
                builder.append('\n');
                handlerNode(child.getChildren(), 1, builder);
                source.sendMessage(StringMessage.text(builder.toString()));
            }
            ConcurrentHashMap<String, RawCommand> rawCommands = commandManager.getRawCommands();
            for (Map.Entry<String, RawCommand> stringRawCommandEntry : rawCommands.entrySet()) {
                source.sendMessage(StringMessage.text("/" + stringRawCommandEntry.getKey()));
            }
            return 0;
        });
    }
    private static void handlerNode(Collection<CommandNode<CommandSender>> node, int tabCount, StringBuilder builder){
        for (CommandNode<CommandSender> commandSenderCommandNode : node) {
            builder.append("\t".repeat(Math.max(0, tabCount)));
            builder.append(commandSenderCommandNode.getUsageText());
            builder.append('\n');
            handlerNode(commandSenderCommandNode.getChildren(), tabCount + 1, builder);
        }
    }
}
