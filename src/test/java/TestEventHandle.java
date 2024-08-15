import cn.wzpmc.Main;
import cn.wzpmc.api.events.message.priv.PrivateMessageEvent;
import cn.wzpmc.api.events.notice.notify.PokeNotifyEvent;
import cn.wzpmc.api.user.Friend;
import cn.wzpmc.configuration.Configuration;
import cn.wzpmc.entities.user.bot.MyBot;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author wzp
 * @version 0.0.4-dev
 * @since 2024/8/16 01:01
 */
public class TestEventHandle {
    @Test
    public void testEventHandle() throws InvocationTargetException, IllegalAccessException {
        Configuration configuration = Main.getConfiguration();
        Friend targetUser = new Friend();
        targetUser.setNickname("test");
        targetUser.setId(Long.valueOf(0));
        MyBot bot = Main.createBot(configuration);
        bot.registerEventHandler(new DemoEventHandler());
        PokeNotifyEvent pokeNotifyEvent = new PokeNotifyEvent();
        pokeNotifyEvent.setTargetId(Long.valueOf(0));
        bot.triggerEvent(pokeNotifyEvent);
        PrivateMessageEvent privateMessageEvent = new PrivateMessageEvent();
        privateMessageEvent.setSender(targetUser);
        bot.triggerEvent(privateMessageEvent);
    }
}
