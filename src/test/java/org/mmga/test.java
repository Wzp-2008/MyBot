package org.mmga;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.interfaces.CommandExecutor;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;
import cn.wzpmc.mybot.pojo.Command;
import cn.wzpmc.mybot.pojo.CommandSender;
import cn.wzpmc.mybot.pojo.User;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
        Yaml yaml = new Yaml();
        JSONObject jsonObject = yaml.loadAs(new FileInputStream(new File("src/test/resources/test.yml")), JSONObject.class);
        String a = jsonObject.getString("a");
        System.out.println(a);
    }
}
