import cn.wzpmc.utils.ReflectionUtils;
import org.junit.jupiter.api.Test;

/**
 * @author wzp
 * @version 0.0.4-dev
 * @since 2024/8/16 00:12
 */
public class TestEventHandler {
    @Test
    public void testEventHandler() {
        System.out.println(ReflectionUtils.loadEvents(new DemoEventHandler()));
    }
}
