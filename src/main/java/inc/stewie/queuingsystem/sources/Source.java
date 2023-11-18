package inc.stewie.queuingsystem.sources;

import inc.stewie.queuingsystem.ProbabilityDistributions;
import inc.stewie.queuingsystem.Request;
import lombok.Getter;

@Getter
public class Source {

    private static int REQUEST_ID = 0;

    private double nextGenerationTime;


    public Source() {
        nextGenerationTime = ProbabilityDistributions.poissonDistribution();
        System.out.println(nextGenerationTime);
    }

    public Request generateRequest(int sourceId) {
        Request request = new Request(REQUEST_ID++, sourceId, nextGenerationTime);
        nextGenerationTime += ProbabilityDistributions.poissonDistribution();
        return request;
    }

}
