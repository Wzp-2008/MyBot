package org.mmga;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.api.MyBotApi;
import cn.wzpmc.mybot.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        Bot bot2 = new Bot(bot1, new URL("http://192.168.123.3:20000"), new ArrayList<Long>());
        MyBotApi bot = new MyBotApi(bot2);
        ArrayList<EssenceMessage> essenceMsgList = bot.getEssenceMsgList(691897264L);
        ArrayList<Variant> abc = bot.getModelShow("abc");
        System.out.println(abc);
    }
}
