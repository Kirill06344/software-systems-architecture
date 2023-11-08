package inc.stewie.queuingsystem.events;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.PriorityQueue;

@Component
public class EventHandler {

    private final PriorityQueue<Event> events = new PriorityQueue<>(Comparator.comparingDouble(Event::getTime));

    public void addEvent(Event event) {
        events.add(event);
    }

    public Event getNextEvent() {
        if (!events.isEmpty()) {
            return events.poll();
        }
        return null;
    }

    public boolean isEmpty() {
        return events.isEmpty();
    }

}
