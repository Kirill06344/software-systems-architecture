package inc.stewie.queuingsystem.entity;

import inc.stewie.queuingsystem.Request;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "buffer")
public class BufferEntity {

    @Id
    private int position;

    private Double time;

    private Integer sourceId;

    private Integer requestNumber;

    protected BufferEntity(){}

    public BufferEntity(int position) {
        this.position = position;
    }

    public void updatePosition(Request request, double time) {
        this.time = time;
        this.sourceId = request.sourceId();
        this.requestNumber = request.id();
    }

    public void pollRequest(double time) {
        this.time = time;
        this.sourceId = null;
        this.requestNumber = null;
    }
}
