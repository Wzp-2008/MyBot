package cn.wzpmc.api;

import lombok.*;

import java.util.UUID;

/**
 * 抽象请求体
 *
 * @author wzp
 * @version 0.0.5-dev
 * @since 2024/8/16 21:36
 */
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
public class Action<REQUEST, RESPONSE> {
    /**
     * 请求回调值（默认随机生成，不建议自己修改）
     *
     * @since 2024/8/23 21:29 v0.0.5-dev
     */
    private final UUID echo = UUID.randomUUID();
    /**
     * 请求操作类型
     *
     * @since 2024/8/23 21:29 v0.0.5-dev
     */
    private Actions action;
    /**
     * 请求参数
     *
     * @since 2024/8/23 21:29 v0.0.5-dev
     */
    private REQUEST params;
}
