package cn.wzpmc.plugins;

import cn.wzpmc.api.plugins.BasePlugin;
import cn.wzpmc.api.plugins.IPluginClassLoader;
import cn.wzpmc.api.user.IBot;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.net.URL;
import java.util.Objects;

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
    private BasePlugin plugin;
    private String name;
    private String version;
    public PluginClassLoader(URL[] urls, IBot bot) {
        super(urls);
        this.bot = bot;
    }
    public void setPlugin(BasePlugin plugin, String name, String version){
        if (Objects.isNull(this.plugin)){
            this.plugin = plugin;
            this.name = name;
            this.version = version;
            return;
        }
        throw new IllegalStateException("Cannot set plugin with a initialized class loader!!!");
    }
}
