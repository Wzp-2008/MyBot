package cn.wzpmc.utils;

/**
 * @author wzp
 * @version 1.0.7
 * @since 2025/10/6 00:53
 */
public class TextUtils {
    private static final int SIMPLIFY_STRING_LENGTH = 20;

    /**
     * 简化字符串
     * @author wzp
     * @since 2025/10/6 00:56 v1.0.7
     * @param original 原始字符串
     * @return 若长度小于等于此类配置中的常量长度则返回原始字符串，否则返回[原始字符串前n个字符]...[原始字符串后n个字符]
     */
    public static String simplifyString(String original) {
        int length = original.length();
        if (length <= SIMPLIFY_STRING_LENGTH) {
           return original;
        }
        return original.substring(0, SIMPLIFY_STRING_LENGTH / 2) + "..." +  original.substring(length - SIMPLIFY_STRING_LENGTH / 2);
    }
}
