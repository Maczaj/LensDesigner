package mma.pszt.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mma.pszt.LensDesigner;

import mma.pszt.utils.Parameters;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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

        if (prevGeneration.isEmpty()){
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
     * selekcja
     */
    private Set<Lens> selectBestLens(Set<Lens> generation) {
        //generation.toArray() sortBy getScore get best half and return
    }

    /**
     * krzyżowanie
     */
    private Set<Lens> miscegenation(Set<Lens> generation) {
        //
    }

    public List<Lens> getListLens() {
        return listLens;
    }

}

