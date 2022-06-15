package cn.wzpmc.mybot.events;

import java.util.Objects;

/**
 * @author wzp
 * @version 1.0.0
 */
public class Event {
    private String eventName;
    private Event(){}
    public Event(String eventName){
        this.eventName = eventName;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return eventName.equals(event.eventName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventName);
    }

}
