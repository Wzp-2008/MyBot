package org.mmga;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.enums.GroupHonorType;
import cn.wzpmc.mybot.interfaces.CommandExecutor;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;
import cn.wzpmc.mybot.pojo.*;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/30
 */
public class test{
    public static void main(String[] args) throws Throwable {
        Logger bot1 = LoggerFactory.getLogger("bot");
        MyBotApi bot = new MyBotApi(new Bot(bot1, new URL("http://192.168.123.3:20000"), new ArrayList<Long>()));
        OcrImageResult ocrImageResult = bot.ocrImage("1dca3e7ef3d187d2ae298ca13a2cd9c2.image");
        bot1.info("{}",ocrImageResult);
        for (TextDetection text : ocrImageResult.getTexts()) {
            String text1 = text.getText();
            bot1.info(text1);
        }
        ///
    }
}
