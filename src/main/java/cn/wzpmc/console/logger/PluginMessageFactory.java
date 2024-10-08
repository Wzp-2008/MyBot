package cn.wzpmc.console.logger;

import cn.wzpmc.plugins.BasePlugin;
import cn.wzpmc.plugins.IPluginClassLoader;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;

import java.lang.reflect.InvocationTargetException;

import static org.apache.logging.log4j.spi.AbstractLogger.DEFAULT_FLOW_MESSAGE_FACTORY_CLASS;

/**
 * 插件消息工厂
 *
 * @author wzp
 * @version 0.0.4-dev
 * @since 2024/8/9 00:35
 */
public class PluginMessageFactory implements MessageFactory {
    private static final MessageFactory baseFactory;

    static {
        try {
            baseFactory = (MessageFactory) DEFAULT_FLOW_MESSAGE_FACTORY_CLASS.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private final String tag;

    public PluginMessageFactory(BasePlugin plugin) {
        IPluginClassLoader classLoader = plugin.getClassLoader();
        String name = classLoader.getName();
        String version = classLoader.getVersion();
        this.tag = '[' + name + '-' + version + "] ";
    }

    @Override
    public Message newMessage(Object message) {
        return baseFactory.newMessage(tag + "{}", message);
    }

    @Override
    public Message newMessage(String message) {
        return baseFactory.newMessage(this.tag + message);
    }

    @Override
    public Message newMessage(String message, Object... params) {
        return baseFactory.newMessage(this.tag + message, params);
    }
}
