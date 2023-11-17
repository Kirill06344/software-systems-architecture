package inc.stewie.queuingsystem;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProbabilityDistributions {

    private static int alpha;

    private static int beta;

    private static int lambda;

    public static double uniformDistribution() {
        return alpha + (beta - alpha) * Math.random();
    }

    public static double poissonDistribution() {
        return -1.0 / lambda * Math.log(Math.random());
    }

    @Value("${model.vars.alpha}")
    public void setAlpha(int alpha) {
        ProbabilityDistributions.alpha = alpha;
    }

    @Value("${model.vars.beta}")
    public void setBeta(int beta) {
        ProbabilityDistributions.beta = beta;
    }
    @Value("${model.vars.lambda}")
    public void setLambda(int lambda) {
        ProbabilityDistributions.lambda = lambda;
    }


}
