package cn.wzpmc.builtin.commands;

import cn.wzpmc.commands.BrigadierCommand;
import cn.wzpmc.entities.Ops;
import cn.wzpmc.message.StringMessage;
import cn.wzpmc.user.CommandSender;
import cn.wzpmc.user.IBot;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * /stop指令
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:26
 */
@RequiredArgsConstructor
public class StopCommand implements BrigadierCommand {
    private final IBot bot;

    @Override
    public LiteralArgumentBuilder<CommandSender> getCommandNode() {
        return LiteralArgumentBuilder.<CommandSender>literal("stop")
                .requires(e -> {
                    Ops ops = bot.getOps();
                    Set<Long> admins = ops.getAdmins();
                    return admins.contains(e.getId());
                })
                .executes((e) -> {
                    CommandSender source = e.getSource();
                    source.sendMessage(StringMessage.text(" !!!MyBot已关闭!!!"));
                    this.bot.stop();
                    System.exit(0);
                    return 0;
                });
    }
}
