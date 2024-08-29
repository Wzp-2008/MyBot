package cn.wzpmc.builtin.commands;

import cn.wzpmc.api.IMainApi;
import cn.wzpmc.api.actions.message.send.SendGroupMessageAction;
import cn.wzpmc.api.actions.message.send.SendPrivateMessageAction;
import cn.wzpmc.commands.BrigadierCommand;
import cn.wzpmc.message.StringMessage;
import cn.wzpmc.user.CommandSender;
import cn.wzpmc.user.IBot;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

/**
 * @author wzp
 * @version 1.0.2
 * @since 2024/8/27 14:54
 */
public class SendCommand implements BrigadierCommand {
    private final IMainApi api;

    public SendCommand() {
        IBot instance = IBot.getInstance();
        this.api = instance.getMainApi();
    }

    @Override
    public LiteralArgumentBuilder<CommandSender> getCommandNode() {
        return LiteralArgumentBuilder.<CommandSender>literal("send")
                .then(LiteralArgumentBuilder.<CommandSender>
                                literal("group").
                        then(RequiredArgumentBuilder.<CommandSender, Long>argument("id", LongArgumentType.longArg()).
                                then(RequiredArgumentBuilder.<CommandSender, String>argument("content", StringArgumentType.greedyString()).
                                        executes(e -> {
                                            CommandSender source = e.getSource();
                                            Long id = e.getArgument("id", Long.class);
                                            String content = e.getArgument("content", String.class);
                                            api.doApiCallSafe(new SendGroupMessageAction(id, StringMessage.text(content)));
                                            source.sendMessage(StringMessage.text("发送成功！"));
                                            return 0;
                                        })
                                )
                        )
                )
                .then(LiteralArgumentBuilder.<CommandSender>
                                literal("user").
                        then(RequiredArgumentBuilder.<CommandSender, Long>argument("id", LongArgumentType.longArg()).
                                then(RequiredArgumentBuilder.<CommandSender, String>argument("content", StringArgumentType.greedyString()).
                                        executes(e -> {
                                            CommandSender source = e.getSource();
                                            Long id = e.getArgument("id", Long.class);
                                            String content = e.getArgument("content", String.class);
                                            api.doApiCallSafe(new SendPrivateMessageAction(id, StringMessage.text(content)));
                                            source.sendMessage(StringMessage.text("发送成功！"));
                                            return 0;
                                        })
                                )
                        )
                );
    }
}
