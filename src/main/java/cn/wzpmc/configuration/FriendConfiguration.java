package cn.wzpmc.configuration;

import cn.wzpmc.plugins.configuration.IFriendConfiguration;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author wzp
 * @version 1.0.3
 * @since 2024/8/30 15:39
 */
@Setter
@EqualsAndHashCode
@ToString
@Getter
public class FriendConfiguration implements IFriendConfiguration {
    /**
     * 是否自动同意好友
     *
     * @since 2024/8/30 15:43 v1.0.3
     */
    private Boolean autoAccept;

    @Override
    public Boolean isAutoAccept() {
        return this.autoAccept;
    }
}
