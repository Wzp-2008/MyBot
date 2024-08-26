package cn.wzpmc.entities;

import lombok.Data;

/**
 * bot运行状态
 *
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:30
 */
@Data
public class BotStatus {
    /**
     * 当前 QQ 在线，null 表示无法查询到在线状态
     *
     * @since 2024/8/23 21:33 v0.0.5-dev
     */
    private boolean online;
    /**
     * 状态符合预期，意味着各模块正常运行、功能正常，且 QQ 在线
     *
     * @since 2024/8/23 21:34 v0.0.5-dev
     */
    private boolean good;
}
