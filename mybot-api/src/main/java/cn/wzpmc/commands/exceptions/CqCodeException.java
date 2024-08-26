package cn.wzpmc.commands.exceptions;

import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.Message;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;

/**
 * @author wzp
 * @version 1.0.0
 * @since 2024/8/25 21:06
 */
public class CqCodeException extends CommandSyntaxException {
    private static final Message errorMessage = new LiteralMessage("Cannot read cq code");

    public CqCodeException() {
        super(new CqCodeExceptionType(errorMessage), errorMessage);

    }

    private static final class CqCodeExceptionType extends SimpleCommandExceptionType {
        public CqCodeExceptionType(Message message) {
            super(message);
        }
    }
}
