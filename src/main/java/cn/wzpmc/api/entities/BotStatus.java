package cn.wzpmc.api.entities;

import lombok.Data;

/**
 * bot运行状态
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:30
 */
@Data
public class BotStatus {
    /**
     * bot是否在线
     * @since 2024/8/23 21:33 v0.0.5-dev
     */
    private boolean online;
    /**
     * bot健康程度
     * @since 2024/8/23 21:34 v0.0.5-dev
     */
    private boolean good;
}
