package vn.ha.tower_defense.observers;

import jdk.jfr.EventType;
import vn.ha.tower_defense.builder.EventBuilder;

public class Event {
    private String message;
    private EventType eventType;

    public Event(String message, EventType eventType) {
        this.message = message;
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public static EventBuilder builder() {
        return new EventBuilder();
    }

    public enum EventType{
        UPDATE, OTHER, SAVE_MAP;
    }
}
