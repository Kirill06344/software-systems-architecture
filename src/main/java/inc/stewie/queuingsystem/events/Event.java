package inc.stewie.queuingsystem.events;


public interface Event {
    void process();

    double getTime();

}
