package cn.wzpmc.api.commands;

import cn.wzpmc.api.user.CommandSender;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;

/**
 * Brigadier指令
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:59
 */
public interface BrigadierCommand extends BaseCommand {
    /**
     * 获取指令节点
     * @author wzp
     * @since 2024/7/31 上午3:16 v0.0.1-dev
     * @return 指令节点
     */
    LiteralArgumentBuilder<CommandSender> getCommandNode();
}
