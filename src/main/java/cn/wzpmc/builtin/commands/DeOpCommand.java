package cn.wzpmc.builtin.commands;

import cn.wzpmc.commands.BrigadierCommand;
import cn.wzpmc.commands.arguments.UserIdArguments;
import cn.wzpmc.entities.Ops;
import cn.wzpmc.entities.user.bot.MyBot;
import cn.wzpmc.message.StringMessage;
import cn.wzpmc.user.CommandSender;
import cn.wzpmc.user.IBot;
import cn.wzpmc.user.group.GroupCommandSender;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;

/**
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/26 16:54
 */
public class DeOpCommand implements BrigadierCommand {
    private final IBot instance;

    public DeOpCommand() {
        this.instance = MyBot.getInstance();
    }

    @Override
    public LiteralArgumentBuilder<CommandSender> getCommandNode() {
        return LiteralArgumentBuilder.
                <CommandSender>literal("deop").
                requires(CommandSender::isAdmin).
                then(RequiredArgumentBuilder.<CommandSender, Long>argument("user", new UserIdArguments()).
                        executes(e -> {
                            Ops ops = instance.getOps();
                            CommandSender source = e.getSource();
                            Long targetId = e.getArgument("user", Long.class);
                            if (source instanceof GroupCommandSender) {
                                if (ops.isAdmin(source.getId())) {
                                    instance.removeOp(targetId);
                                    source.sendMessage(StringMessage.text("已为用户：" + targetId + "移除总OP权限"));
                                } else {
                                    Long groupId = ((GroupCommandSender) source).getGroupId();
                                    instance.removeOp(groupId, targetId);
                                    source.sendMessage(StringMessage.text("已为用户：" + targetId + "移除群：" + groupId + "的OP权限"));
                                }
                                return 0;
                            }
                            instance.removeOp(targetId);
                            source.sendMessage(StringMessage.text("已为用户：" + targetId + "移除总OP权限"));
                            return 0;
                        }).
                        then(LiteralArgumentBuilder.<CommandSender>literal("group")
                                .then(RequiredArgumentBuilder.<CommandSender, Long>argument("group", LongArgumentType.longArg()).
                                        executes(e -> {
                                            Long targetGroupId = e.getArgument("group", Long.class);
                                            Long targetId = e.getArgument("user", Long.class);
                                            CommandSender source = e.getSource();
                                            if (source instanceof GroupCommandSender) {
                                                if (!instance.isBotOp(targetGroupId, source.getId())) {
                                                    source.sendMessage(StringMessage.text("权限不足！"));
                                                    return 0;
                                                }
                                            }
                                            instance.removeOp(targetGroupId, targetId);
                                            source.sendMessage(StringMessage.text("已为用户：" + targetId + "移除群：" + targetGroupId + "的OP权限"));
                                            return 0;
                                        })
                                ).requires((e) -> e instanceof GroupCommandSender).executes(e -> {
                                    GroupCommandSender source = (GroupCommandSender) e.getSource();
                                    Long groupId = source.getGroupId();
                                    Long targetId = e.getArgument("user", Long.class);
                                    instance.removeOp(groupId, targetId);
                                    source.sendMessage(StringMessage.text("已为用户：" + targetId + "移除群：" + groupId + "的OP权限"));
                                    return 0;
                                })
                        )
                );
    }
}
