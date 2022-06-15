package cn.wzpmc.mybot.cq;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wzp
 * @version 1.0.0
 */
public class CqCode {
    private final String code;
    private final Map<String,String> args;

    public CqCode(String code, Map<String,String> args){
        this.code = code;
        this.args = args;
    }
    public CqCode(String code){
        this.code = code;
        this.args = new HashMap<>();
    }
    public void addArgv(String key,String value){
        args.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[CQ:");
        builder.append(code);
        builder.append(",");
        for (String s : args.keySet()) {
            builder.append(s).append("=").append(args.get(s)).append(",");
        }
        int i = builder.lastIndexOf(",");
        builder.setCharAt(i,']');
        return builder.toString();
    }
}
