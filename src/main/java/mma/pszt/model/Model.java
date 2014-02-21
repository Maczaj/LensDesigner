package mma.pszt.model;

import lombok.Getter;
import lombok.Setter;
import mma.algorithm.evolution.EvolutionaryAlgorithm;
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
     
    //TODO przerobić model pod nowe interfejsy (wywalić co zbędne)
    private EvolutionaryAlgorithm<EvaluatedLens> algorithm;

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

