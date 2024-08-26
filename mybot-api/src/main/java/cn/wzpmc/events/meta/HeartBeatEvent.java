package cn.wzpmc.events.meta;

import cn.wzpmc.entities.BotStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/8/1 下午10:30
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class HeartBeatEvent extends MetaEvent {
    /**
     * 状态信息
     *
     * @since 2024/8/1 下午10:32 v0.0.2-dev
     */
    private BotStatus status;
    /**
     * 到下次心跳的间隔（ms）
     *
     * @since 2024/8/1 下午10:32 v0.0.2-dev
     */
    private Long interval;
}
