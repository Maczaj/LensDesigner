package mma.pszt.model;

import lombok.Getter;
import lombok.Setter;
import mma.pszt.utils.Parameters;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

public class Model {

    private static final Logger logger = Logger.getLogger(Model.class.getName());
    @Getter
    @Setter
    private Parameters parameters;
    @Getter
    @Setter
    private EvaluatedLens lens;
    private double sigma;
    private List<Integer> lastLensChoices = new LinkedList<>();
    private final int stepsToChangeSigma = 10; // m ze skryptu
    private final double c1 = 0.82;
    private final double c2 = 1.2;

    public Model() {
        sigma = 2;
    }
    
    /**
     * Next iteration of mi + lambda algorithm
     * @return generation number, if satisfying lens is detected, 0 otherwise
     */
    public int nextIteration() {
        EvaluatedLens newLens = new EvaluatedLens(new Lens(lens.getLens(), sigma), parameters);

        return 0;
    }
}

