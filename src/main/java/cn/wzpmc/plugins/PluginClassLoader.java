package cn.wzpmc.plugins;

import cn.wzpmc.api.plugins.BasePlugin;
import cn.wzpmc.api.plugins.IPluginClassLoader;
import cn.wzpmc.api.user.IBot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.URL;

/**
 * 插件类加载器实现
 * @author wzp
 * @version 0.0.2-dev
 * @since 2024/7/31 下午7:12
 */
@Getter
@EqualsAndHashCode(callSuper = true)
@ToString
public class PluginClassLoader extends IPluginClassLoader {
    private final IBot bot;
    @Setter
    private BasePlugin plugin;
    public PluginClassLoader(URL[] urls, IBot bot) {
        super(urls);
        this.bot = bot;
    }
}
