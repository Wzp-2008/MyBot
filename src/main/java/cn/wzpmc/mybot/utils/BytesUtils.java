package cn.wzpmc.mybot.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * @author 33572
 * @version 1.0.0
 */
public class BytesUtils {
    private static boolean unclosed;
    private static StringBuffer buffer;
    public static final Logger log = LoggerFactory.getLogger("ByteHandler");

    public static String getStringFromByteBuf(ByteBuf byteBuf, int remove) {
        //去除前四位字符
        try {
            byteBuf.readBytes(remove);
            CharSequence charSequence = byteBuf.readCharSequence(byteBuf.readableBytes(), StandardCharsets.UTF_8);
            return charSequence.toString();
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            int i = byteBuf.readableBytes();
            System.out.println(i);
            System.out.println(remove);
            return "";
        }

    }

    public static JSONObject getJsonFromByteBuf(ByteBuf byteBuf) {
        String fullJson;
        if (unclosed) {
            String m = BytesUtils.getStringFromByteBuf(byteBuf, 0);
            buffer.append(m);
            fullJson = buffer.toString();
        } else {
            fullJson = BytesUtils.getStringFromByteBuf(byteBuf, 4);
        }
        JSONObject data;
        try {
            data = JSON.parseObject(fullJson);
            unclosed = false;
            buffer = new StringBuffer();
        } catch (JSONException | ArrayIndexOutOfBoundsException e) {
            unclosed = true;
            buffer.append(fullJson);
            return null;
        }
        return data;
    }
}
