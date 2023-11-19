package inc.stewie.queuingsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "sources")
@Getter
public class SourceEntity {

    @Id
    private int id;
    private double time;
    private int requestAmount;
    private int refuseAmount;
    protected SourceEntity(){}

    public SourceEntity(int id) {
        this.id = id;
    }

    public void addRequest(double time) {
        requestAmount++;
        this.time = time;
    }

    public void addRefuse(double time) {
        refuseAmount++;
        this.time = time;
    }
}
