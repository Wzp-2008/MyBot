package cn.wzpmc.configuration;

import cn.wzpmc.plugins.configuration.IGroupConfiguration;
import lombok.Data;

/**
 * 群聊相关配置实现
 *
 * @author wzp
 * @version 1.0.3
 * @since 2024/8/31 23:56
 */
@Data
public class GroupConfiguration implements IGroupConfiguration {
    /**
     * 是否自动同意好友
     *
     * @since 2024/8/30 15:43 v1.0.3
     */
    private boolean autoAccept;
}
