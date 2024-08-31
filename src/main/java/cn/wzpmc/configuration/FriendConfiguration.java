package cn.wzpmc.configuration;

import cn.wzpmc.plugins.configuration.IFriendConfiguration;
import lombok.Data;

/**
 * @author wzp
 * @version 1.0.3
 * @since 2024/8/30 15:39
 */
@Data
public class FriendConfiguration implements IFriendConfiguration {
    /**
     * 是否自动同意好友
     *
     * @since 2024/8/30 15:43 v1.0.3
     */
    private boolean autoAccept;
}
