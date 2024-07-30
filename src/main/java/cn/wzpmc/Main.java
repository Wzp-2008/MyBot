package cn.wzpmc;

import cn.wzpmc.configuration.Configuration;
import cn.wzpmc.network.WebSocketConnectionHandler;
import cn.wzpmc.utils.TemplateFileUtils;
import cn.wzpmc.utils.YamlUtils;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

@Log4j2
public class Main {
    private static final String DEFAULT_CONFIGURATION_FILE_PATH = "templates/config.yaml";
    @SneakyThrows
    public static void main(String[] args) {
        log.info("启动MyBot...");
        File configurationFile = new File("config.yaml");
        if (TemplateFileUtils.saveDefaultConfig(Main.class.getClassLoader(), DEFAULT_CONFIGURATION_FILE_PATH, configurationFile)) {
            log.debug("创建日志文件成功！");
            log.info("首次启动，默认配置文件已创建，请填写后再次启动MyBot！");
            return;
        }
        log.debug("读取配置文件 {}", configurationFile.getAbsolutePath());
        Configuration configuration = YamlUtils.readYamlFile(configurationFile, Configuration.class);
        URI uri;
        try {
             uri = new URI(configuration.getWebsocket());
        } catch (URISyntaxException e) {
            log.error("无法解析websocket地址");
            return;
        }
        WebSocketConnectionHandler webSocketConnectionHandler = new WebSocketConnectionHandler();
        ChannelFuture future = webSocketConnectionHandler.connect(uri);
        Channel channel = future.sync().channel();

    }
}