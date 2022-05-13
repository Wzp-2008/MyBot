package cn.wzpmc.mybot.pojo;

import cn.wzpmc.mybot.enums.MessageSenderType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.slf4j.Logger;

/**
 * @author wzp
 * @version 1.0.0
 * @date 2022/5/4
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
