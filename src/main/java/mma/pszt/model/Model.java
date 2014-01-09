package mma.pszt.model;

import mma.pszt.LensDesigner;
import mma.pszt.utils.Parameters;
import org.apache.log4j.Logger;

import java.util.*;

public class Model {

    private static final Logger logger = Logger.getLogger(LensDesigner.class.getName());
    private final Parameters parameters;
    private List<Lens> listLens;

    public Model() {
        parameters = new Parameters(0, 0, 0, 0.0, 0.0, 0.0);
    }

    public Model(Parameters parameters) {

        this.parameters = parameters;
    }

    /**
     * wygeneruj nową generacje
     */
    public Set<Lens> nextGeneration(Set<Lens> prevGeneration) {

        Set<Lens> selectedBestLens = new HashSet<>();

        if (prevGeneration.isEmpty()) {
            for (int i = 0; i < parameters.getNumberOfLens(); ++i) {
                selectedBestLens.add(new Lens());
            }
        } else {
            selectedBestLens = selectBestLens(prevGeneration);
        }

        Set<Lens> newGeneration = miscegenation(selectedBestLens);

        return newGeneration;
    }

    /**
     * selekcja. generation.toArray().sortBy(getScore) and return best half
     */
    public HashSet<Lens> selectBestLens(Set<Lens> generation) {
        List arrayOfLens = new ArrayList(generation);

        Collections.sort(arrayOfLens, new Comparator<Lens>() {
            @Override
            public int compare(Lens lens, Lens lens2) {
                return Integer.valueOf(lens.getScore()).compareTo(lens2.getScore());
            }
        });
        return new HashSet<Lens>(arrayOfLens.subList(0, Math.round(generation.size() / 2)));
    }

    /**
     * krzyżowanie
     */
    private Set<Lens> miscegenation(Set<Lens> generation) {
        //
        return new HashSet<>();
    }

    public List<Lens> getListLens() {
        return listLens;
    }

}

