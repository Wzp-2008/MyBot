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
    private boolean online;
    private boolean good;
}
