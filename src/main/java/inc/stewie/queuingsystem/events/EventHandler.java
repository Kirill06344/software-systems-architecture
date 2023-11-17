package inc.stewie.queuingsystem.events;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.PriorityQueue;

@Component
public class EventHandler {

    private final PriorityQueue<Event> events = new PriorityQueue<>(Comparator.comparingDouble(Event::getTime));

    private double time;

    public void addEvent(Event event) {
        events.add(event);
    }

    public Event getNextEvent() {
        if (!events.isEmpty()) {
            Event event = events.poll();
            time = event.getTime();
            return event;
        }
        return null;
    }

    public boolean isEmpty() {
        return events.isEmpty();
    }

    public double getCurrentTime() {
        return time;
    }

}
