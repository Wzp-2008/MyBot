package org.mmga.YourMother;

import cn.wzpmc.mybot.Bot;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;
import org.slf4j.Logger;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 * @date 2022/4/30
 */
public class Main extends MyBotPlugin {
    @Override
    public boolean onLoad() {
        Logger logger = getLogger();
        logger.info("YourMother已成功加载（？");
        Bot bot = this.getBot();
        bot.registerCommand(this.getCommand("nm"),new Nm());
        bot.registerEvent(this,new SendMessage());
        return true;
    }
}
