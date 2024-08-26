import cn.wzpmc.api.message.json.JsonMessagePart;
import cn.wzpmc.api.utils.CqCodeUtils;
import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/26 14:14
 */
public class TestCqUtils {
    @Test
    public void testIsCq(){
        System.out.println(CqCodeUtils.isCQ("[CQ:at,qq=123456789]"));
    }
    @Test
    public void testCqParser(){
        Map<String, String> parse = CqCodeUtils.parse("[CQ:at,qq=123456789,name=123]");
        for (Map.Entry<String, String> stringStringEntry : parse.entrySet()) {
            System.out.println(stringStringEntry);
        }
    }
    @Test
    public void testParseCq(){
        JsonMessagePart jsonMessagePart = CqCodeUtils.parseToPart("[CQ:at,qq=123456789,name=123]");
        System.out.println(jsonMessagePart);
    }
}
