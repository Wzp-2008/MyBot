package cn.wzpmc.commands;

import cn.wzpmc.api.commands.BrigadierCommand;
import cn.wzpmc.api.user.CommandSender;
import cn.wzpmc.api.user.IBot;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import lombok.RequiredArgsConstructor;

/**
 * /stop指令
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
                .requires(CommandSender::isAdmin)
                .executes((e) -> {
                    this.bot.stop();
                    return 0;
                });
    }
}
