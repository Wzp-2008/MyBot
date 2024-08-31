package cn.wzpmc.configuration;

import cn.wzpmc.plugins.configuration.IGroupConfiguration;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 群聊相关配置实现
 *
 * @author wzp
 * @version 1.0.3
 * @since 2024/8/31 23:56
 */
@Setter
@EqualsAndHashCode
@ToString
@Getter
public class GroupConfiguration implements IGroupConfiguration {
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
