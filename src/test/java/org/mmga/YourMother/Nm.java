package org.mmga.YourMother;

import cn.wzpmc.mybot.cq.At;
import cn.wzpmc.mybot.interfaces.CommandExecutor;
import cn.wzpmc.mybot.pojo.Command;
import cn.wzpmc.mybot.pojo.GroupMessage;
import cn.wzpmc.mybot.pojo.Message;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qicaijinghua_
 * @version 1.0.0
 * @date 2022/4/30
 */
public class Nm implements CommandExecutor {
    public static final ConcurrentHashMap<Long,String> users = new ConcurrentHashMap<>();
    @Override
    public boolean execute(Command command) {
        ArrayList<String> body = command.getBody();
        GroupMessage rawMessage = command.getRawMessage();
        if (body.size() == 0){
           rawMessage.reply("您就输个命令是吗？");
       } else if (body.size() == 1) {
            String s = body.get(0);
            Long qqFromCode = At.getQqFromCode(s);
            //a = {123:"abc",567:"def"}
            //key,value
            //a[key]
            //a[key] == null
            //return value
            //else
            //return a[key]
            if (qqFromCode != -1){
                String orDefault = users.getOrDefault(qqFromCode, "");
                if(orDefault.equals("")) {
                    rawMessage.reply("已开" + "始向" + qqFromCode + "发送说nm话呢");
                    users.put(qqFromCode,"说nm话呢");
                }else {
                    rawMessage.reply("已停止向"+qqFromCode+"发送"+users.get(qqFromCode));
                    users.put(qqFromCode,"");
                }
            }else {
                rawMessage.reply("您完全不@人是吗？");
            }
        }else if (body.size() == 2){
            String s = body.get(0);
            Long qqFromCode = At.getQqFromCode(s);
            String s1 = body.get(1);
            String orDefault = users.getOrDefault(qqFromCode, "");
            if (qqFromCode != -1){
                if(orDefault.equals("")) {
                    rawMessage.reply("已开始向" + qqFromCode + "发送" + s1);
                    users.put(qqFromCode,s1);
                }else{
                    rawMessage.reply("已停止向"+qqFromCode+"发送"+users.get(qqFromCode));
                    users.put(qqFromCode,"");
                }
            }else {
                rawMessage.reply("您完全不@人是吗？");
            }
        }
        return true;
    }
}


