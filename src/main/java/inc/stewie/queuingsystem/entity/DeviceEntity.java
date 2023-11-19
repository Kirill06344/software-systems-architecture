package inc.stewie.queuingsystem.entity;

import inc.stewie.queuingsystem.devices.DeviceState;
import inc.stewie.queuingsystem.devices.DeviceStorage;
import jakarta.persistence.*;

@Entity
@Table(name = "devices")
public class DeviceEntity {
    @Id
    private int id;

    private double time;

    @Enumerated(EnumType.STRING)
    private DeviceState state;

    private int requestAmount;
    protected DeviceEntity(){}

    public DeviceEntity(int id) {
        this.id = id;
    }

    public void processRequest(double time) {
        state = DeviceState.BUSY;
        this.time = time;
    }

    public void free(double time) {
        this.time = time;
        state = DeviceState.FREE;
        requestAmount++;
    }
}
