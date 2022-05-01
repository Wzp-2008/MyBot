package org.mmga;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.api.MyBotApi;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.URL;
import java.util.ArrayList;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/4/30
 */
@Slf4j
public class test {
    @SneakyThrows
    public static void main(String[] args) {
        MyBotApi myBotApi = new MyBotApi(new Bot(log, new URL("http://192.168.123.3:20000"),new ArrayList<Long>()));
        System.out.println(myBotApi.getFriendList());
    }
}
