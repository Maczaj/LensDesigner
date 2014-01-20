package mma.pszt.model;

import lombok.Getter;
import mma.pszt.LensDesigner;
import mma.pszt.utils.Parameters;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

import java.util.*;

public class Model {

    private static final Logger logger = Logger.getLogger(Model.class.getName());
    private final Parameters parameters;
    @Getter
    private Lens lens;
    private double sigma;
    private List<Integer> lastLensChoices = new LinkedList<>();
    private int noGeneration = 1;
    private final int stepsToChangeSigma = 10; // m ze skryptu
    private final double c1 = 0.82;
    private final double c2 = 1.2;

    public Model() {
        sigma = 1;
        parameters = new Parameters(0, 0, 0.0, 0.0, 0.0);
        lens = new Lens();
    }

    public Model(Parameters parameters) {
        sigma = 1;
        this.parameters = parameters;
        lens = new Lens();
    }

    public void nextIteration() {
        Lens newLens = new Lens(lens, sigma);
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
            if (ro < 1.0/5) {
                sigma = c1 * sigma;
            } else if (ro > 1.0/5) {
                sigma = c2 * sigma;
            }
            lastLensChoices.clear();
        }
        noGeneration++;
        System.out.println(noGeneration + " " + sigma);
    }
}

