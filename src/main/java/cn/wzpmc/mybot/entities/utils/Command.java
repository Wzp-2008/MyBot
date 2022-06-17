package cn.wzpmc.mybot.entities.utils;

import cn.wzpmc.mybot.entities.messages.BaseMessage;
import cn.wzpmc.mybot.interfaces.MyBotPlugin;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author wzp
 * @version 1.0.0
 */
@ToString
@Getter
@Setter
public class Command {
    private final String head;
    private final ArrayList<String> body;
    private final MyBotPlugin plugin;
    private final BaseMessage rawMessage;

    public Command(String command, BaseMessage message) {
        String[] s = command.split(" ");
        int len = s.length;
        this.head = s[0];
        this.body = new ArrayList<>(Arrays.asList(s).subList(1, len));
        this.plugin = null;
        this.rawMessage = message;
    }

    public Command(String head, MyBotPlugin plugin) {
        this.head = head;
        this.body = new ArrayList<>();
        this.plugin = plugin;
        this.rawMessage = null;
    }

    public Command(String head, ArrayList<String> body, MyBotPlugin plugin) {
        this.head = head;
        this.body = body;
        this.plugin = plugin;
        this.rawMessage = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Command command = (Command) o;
        return head.equals(command.head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(head);
    }
}
