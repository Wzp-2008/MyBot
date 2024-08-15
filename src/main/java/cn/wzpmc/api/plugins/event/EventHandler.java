package cn.wzpmc.api.plugins.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使用此注解以指定一个方法为事件执行器
 * @author wzp
 * @version 0.0.4-dev
 * @since 2024/8/15 23:47
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
}
