import cn.wzpmc.api.events.Event;
import cn.wzpmc.api.events.message.priv.PrivateMessageEvent;
import cn.wzpmc.api.events.notice.notify.PokeNotifyEvent;
import cn.wzpmc.api.plugins.event.EventHandler;

/**
 * @author wzp
 * @version 0.0.4-dev
 * @since 2024/8/16 01:02
 */
public final class DemoEventHandler {
    @EventHandler
    public void onMessage(PrivateMessageEvent event) {
        System.out.println(event);
        System.out.println("Called 1");
    }
    @EventHandler
    public void onMessage2(PrivateMessageEvent event) {
        System.out.println(event);
        System.out.println("Called 2");
    }
    @EventHandler
    public void onPoke(PokeNotifyEvent event) {
        System.out.println(event);
        System.out.println("Called poke");
    }
    public void otherMethod(Event event){
        System.err.println(event);
        System.err.println("otherMethod shouldn't called!");
    }
    @EventHandler
    public void wrongMethod(Event event, String wrongArgs){
        System.err.println(event);
        System.err.println(wrongArgs);
        System.err.println("wrongMethod shouldn't called!");
    }
    @EventHandler
    public void wrongMethod(String wrongArgs){
        System.err.println(wrongArgs);
        System.err.println("wrongMethod2 shouldn't called!");
    }
}
