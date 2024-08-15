package cn.wzpmc.entities.event;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author wzp
 * @version 0.0.4-dev
 * @since 2024/8/15 23:53
 */
@Data
public class EventHandlerMethod {
    private final Object object;
    private final Method method;
}
