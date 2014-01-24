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
    private EvaluatedLens lens;
    private double sigma;
    private List<Integer> lastLensChoices = new LinkedList<>();
    private int noGeneration = 1;
    private final int stepsToChangeSigma = 10; // m ze skryptu
    private final double c1 = 0.82;
    private final double c2 = 1.2;

//    public Model() {
//        sigma = 1;
//        this.parameters = new Parameters(0, 0, 0.0, 0.0, 0.0);
//        this.lens = new EvaluatedLens(new Lens(), parameters);
//    }

    public Model(Parameters parameters) {
        sigma = 2;
        this.parameters = parameters;
        this.lens = new EvaluatedLens(new Lens(), parameters);
    }

    public void nextIteration() {
        EvaluatedLens newLens = new EvaluatedLens(new Lens(lens.getLens(), sigma), parameters);
        if (lens.getScore() <= newLens.getScore()) {
            lens = newLens;
            lastLensChoices.add(1);
        } else {
            lastLensChoices.add(0);
        }
        assert (lastLensChoices.size() <= 10);
        int count = 0;
        for (Integer it : lastLensChoices) {
            if (it == 1) {
                count++;
            }
        }
        if (noGeneration % stepsToChangeSigma == 0) {
            double ro = (count * 1.0) / stepsToChangeSigma;
            if (ro < 0.2) {
                sigma = c1 * sigma;
            } else if (ro > 0.2) {
                sigma = c2 * sigma;
            }
            lastLensChoices.clear();
        }
        noGeneration++;
        System.out.println(noGeneration + " " + sigma);
    }
}

