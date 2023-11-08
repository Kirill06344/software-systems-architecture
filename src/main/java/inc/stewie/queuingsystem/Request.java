package inc.stewie.queuingsystem;

import lombok.Getter;
public record Request(int id, int sourceId, double creationTime) {
}
