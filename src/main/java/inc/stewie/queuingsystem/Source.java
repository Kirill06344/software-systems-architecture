package inc.stewie.queuingsystem;

import lombok.Getter;

@Getter
public class Source {

    private final int lambda;

    private double nextGenerationTime;

    public Source(int lambda) {
        this.lambda = lambda;
        nextGenerationTime = calculateIntervalBetweenRequests();
    }

    public Request generateRequest(int requestId, int sourceId) {
        Request request = new Request(requestId, sourceId, nextGenerationTime);
        nextGenerationTime += calculateIntervalBetweenRequests();
        return request;
    }

    public double calculateIntervalBetweenRequests() {
        return -1.0 / lambda * Math.log(Math.random());
    }

}
