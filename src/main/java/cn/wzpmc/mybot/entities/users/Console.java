package cn.wzpmc.mybot.entities.users;

import cn.wzpmc.mybot.enums.MessageSenderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;

/**
 * @author wzp
 * @version 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Console extends MessageSender {
    private Logger logger;
    public Console(Logger logger) {
        super(MessageSenderType.console,0L,"CONSOLE");
        this.logger = logger;
    }
}
