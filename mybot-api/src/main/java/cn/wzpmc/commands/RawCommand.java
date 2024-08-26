package cn.wzpmc.commands;

import cn.wzpmc.user.CommandSender;

import java.util.List;

/**
 * 原始指令
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午2:27
 */
public interface RawCommand extends BaseCommand {
    /**
     * 当指令执行时
     *
     * @param commandSender 指令发送者
     * @param arguments     指令参数，如指令foo若执行/foo a b c则arguments为{"a", "b", "c"}
     * @return 指令是否执行成功
     * @author wzp
     * @since 2024/7/31 上午2:57 v0.0.1-dev
     */
    boolean onExecute(CommandSender commandSender, List<String> arguments);

    /**
     * 指令补全
     *
     * @param commandSender 指令发送者
     * @param arguments     指令参数，如指令foo若执行/foo a b c则arguments为{"a", "b", "c"}
     * @return 当前需要提示的子命令
     * @author wzp
     * @since 2024/7/31 上午2:58 v0.0.1-dev
     */
    default List<String> onTabComplete(CommandSender commandSender, List<String> arguments) {
        return List.of();
    }
}
