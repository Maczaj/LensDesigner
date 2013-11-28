package mma.pszt.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Arkadiusz Szlachetka
 */
@Getter @Setter @AllArgsConstructor
public class Parameters {

    private Integer numberOfPoints;
    private Integer numberOfRays;
    private Integer numberOfLens;
    private Double refractiveIndex;
    private Double focusingAccuracy;
    private Double mutationRate;
}
