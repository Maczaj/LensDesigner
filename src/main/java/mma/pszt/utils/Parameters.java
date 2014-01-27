package mma.pszt.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Arkadiusz Szlachetka
 */
@Getter
@Setter
@AllArgsConstructor
public class Parameters {


    private Integer numberOfPoints;
    private Integer numberOfRays;
    private Double refractiveIndex;
    private Double focusingAccuracy;
    private Double minimumSigma;

    public Parameters() {
    }

    public boolean checkIfParamsCorrect() {
        return numberOfPoints != null &&
                numberOfRays != null &&
                focusingAccuracy != null &&
                refractiveIndex != null &&
                minimumSigma != null;
    }

    @Override
    public String toString() {
        return "numberOfPoints : " + numberOfPoints + " numberOfRays: " + numberOfRays;
    }
}
