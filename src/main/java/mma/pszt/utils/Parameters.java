package mma.pszt.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Arkadiusz Szlachetka
 */

public class Parameters {

    @Getter @Setter Integer numberOfPoints;
    @Getter @Setter Integer numberOfRays;
    @Getter @Setter Double refractiveIndex;
    @Getter @Setter Double focusingAccuracy;

}
