package inc.stewie.queuingsystem;

import lombok.Getter;

@Getter
public class Source {

    private static int SOURCE_IDENTITY = 0;

    private final int id;

    private final int lambda;

    private double nextGenerationTime;

    public Source(int lambda) {
        this.lambda = lambda;
        this.id = SOURCE_IDENTITY++;
        nextGenerationTime = calculateIntervalBetweenRequests();
    }

    public Request generateRequest(int requestId) {
        Request request = new Request(requestId, id, nextGenerationTime);
        nextGenerationTime += calculateIntervalBetweenRequests();
        return request;
    }


    public double calculateIntervalBetweenRequests() {
        return -1.0 / lambda * Math.log(Math.random());
    }

}
