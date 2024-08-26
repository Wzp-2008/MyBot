package cn.wzpmc.plugins;

import cn.wzpmc.commands.BrigadierCommand;
import cn.wzpmc.commands.RawCommand;

/**
 * 指令管理器
 *
 * @author wzp
 * @version 0.0.1-dev
 * @since 2024/7/31 上午3:40
 */
public interface ICommandManager {
    /**
     * 注册原始指令
     *
     * @param rawCommand 原始指令
     * @param name       指令名称
     * @author wzp
     * @since 2024/7/31 上午3:34 v0.0.1-dev
     */
    void registerCommand(RawCommand rawCommand, String name);

    /**
     * 注册Brigadier指令
     *
     * @param brigadierCommand 指令对象
     * @author wzp
     * @since 2024/7/31 上午3:35 v0.0.1-dev
     */
    void registerCommand(BrigadierCommand brigadierCommand);
}
