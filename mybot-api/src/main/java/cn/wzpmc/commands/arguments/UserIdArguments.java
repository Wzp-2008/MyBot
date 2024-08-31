package cn.wzpmc.commands.arguments;

import cn.wzpmc.commands.exceptions.CqCodeException;
import cn.wzpmc.message.json.JsonMessagePart;
import cn.wzpmc.message.json.parts.At;
import cn.wzpmc.utils.CqCodeUtils;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.exceptions.BuiltInExceptions;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

/**
 * 用户ID消息类型
 *
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 21:02
 */
public class UserIdArguments implements ArgumentType<Long> {
    @Override
    public Long parse(StringReader stringReader) throws CommandSyntaxException {
        StringBuilder builder = new StringBuilder();
        char read = stringReader.read();
        if (read == '[') {
            builder.append('[');
            while (stringReader.canRead()) {
                read = stringReader.read();
                builder.append(read);
                if (read == ']') {
                    break;
                }
            }
            String s = builder.toString();
            if (CqCodeUtils.isCQ(s)) {
                JsonMessagePart jsonMessagePart = CqCodeUtils.parseToPart(s);
                if (jsonMessagePart instanceof At) {
                    return Long.parseLong(((At) jsonMessagePart).getQq());
                }
                throw new CqCodeException();
            }
        } else {
            String s = stringReader.readUnquotedString();
            String s1 = read + s;
            try {
                return Long.parseLong(s1);
            } catch (NumberFormatException ignored) {
            }
        }
        throw new CommandSyntaxException(new BuiltInExceptions().readerInvalidLong(), new LiteralMessage("Cannot read long from user id"));
    }
}
