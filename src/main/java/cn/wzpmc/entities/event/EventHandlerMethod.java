package cn.wzpmc.entities.event;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * 事件处理器
 * @author wzp
 * @version 0.0.4-dev
 * @since 2024/8/15 23:53
 */
@Data
public class EventHandlerMethod {
    /**
     * 处理器类
     * @since 2024/8/23 21:47 v0.0.5-dev
     */
    private final Object object;
    /**
     * 处理的方法
     * @since 2024/8/23 21:47 v0.0.5-dev
     */
    private final Method method;
}
