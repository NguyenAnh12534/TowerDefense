package vn.ha.tower_defense.builder;

import vn.ha.tower_defense.observers.Event;
import vn.ha.tower_defense.observers.Event.EventType;

public class EventBuilder {
    private String message;
    private EventType eventType;

    public EventBuilder message(String message) {
        this.message = message;
        return this;
    }

    public EventBuilder eventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public Event build() {
        return new Event(this.message, this.eventType);
    }

}
