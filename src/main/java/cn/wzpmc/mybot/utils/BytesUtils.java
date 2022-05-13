package cn.wzpmc.mybot.utils;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

/**
 * @author 33572
 */
public class BytesUtils {
    public static String getStringFromByteBuf(ByteBuf byteBuf,int remove){
        //去除前四位字符
        try {
            byteBuf.readBytes(remove);
            CharSequence charSequence = byteBuf.readCharSequence(byteBuf.readableBytes(), StandardCharsets.UTF_8);
            byteBuf.release();
            return charSequence.toString();
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            int i = byteBuf.readableBytes();
            System.out.println(i);
            System.out.println(remove);
            byteBuf.release();
            return "";
        }
    }
}
