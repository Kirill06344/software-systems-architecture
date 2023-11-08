package inc.stewie.queuingsystem.events;

import inc.stewie.queuingsystem.Request;

public interface Event {
    void process();

    Request getRequest();

    double getTime();

}
