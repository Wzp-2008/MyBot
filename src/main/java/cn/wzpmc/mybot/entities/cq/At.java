package cn.wzpmc.mybot.entities.cq;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author wzp
 * @version 1.0.0
 */
public class At extends CqCode{
    public static Pattern pattern = Pattern.compile("\\[CQ:at,qq=(.*)]");
    public At(long qq) {
        super("at");
        this.addArgv("qq",String.valueOf(qq));
    }

    /**
     * 从CQ码中提取出qq号
     * @param cq CQ码
     * @return 若没找到则返回-1，找到则返回qq号
     */
    public static Long getQqFromCode(String cq){
        boolean matches = cq.matches("\\[CQ:at,qq=(.*)]");
        if(!matches){
            return -1L;
        }else{
            Matcher matcher = pattern.matcher(cq);
            boolean b = matcher.find();
            if(!b){
                return -1L;
            }
            return Long.valueOf(matcher.group(1));
        }
    }
}
