package inc.stewie.queuingsystem;

import lombok.Getter;

@Getter
public class Source {

    private static int SOURCE_IDENTITY = 0;

    private final int id;

    private final int lambda;

    public Source(int lambda) {
        this.lambda = lambda;
        this.id = SOURCE_IDENTITY++;
    }


    public double generateRequestTime() {
        return -1.0 / lambda * Math.log(Math.random());
    }

}
