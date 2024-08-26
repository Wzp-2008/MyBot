package cn.wzpmc.api.commands.arguments;

import cn.wzpmc.api.message.json.JsonMessagePart;
import cn.wzpmc.api.utils.CqCodeUtils;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

/**
 * 消息段参数类型
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 01:18
 */
public class MessagePartArgument implements ArgumentType<JsonMessagePart> {
    @Override
    public JsonMessagePart parse(StringReader stringReader) throws CommandSyntaxException {
        String s = stringReader.readStringUntil(' ');
        if (!CqCodeUtils.isCQ(s)){
            throw new CommandSyntaxException(new SimpleCommandExceptionType(new LiteralMessage("MessagePart")), new LiteralMessage("Cannot read message part"));
        }
        return CqCodeUtils.parseToPart(s);
    }
}
