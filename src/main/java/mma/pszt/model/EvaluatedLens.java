package mma.pszt.model;

import lombok.Getter;
import mma.pszt.utils.Point;

import java.util.Set;

/**
 * @author Maciej Jagiello
 */
@Getter
public class EvaluatedLens {

    private Ray ray;
    private Lens lens;
    private Set<Point> intersectionPoints;
}
