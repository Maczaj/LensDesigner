package mma.pszt.model;

import lombok.Getter;
import lombok.Setter;
import mma.pszt.utils.Parameters;
import mma.pszt.utils.Point;

import java.util.Set;

/**
 * Lens with rays, evaluated score and calculated intersection points
 *
 * @author Maciej Jagiello
 */
@Getter
public class EvaluatedLens {

    private final Set<Ray> rays;
    private final Lens lens;
    private Set<Point> intersectionPoints;
    @Setter
    private int score = 0;

    public EvaluatedLens(final Lens lens, Parameters parameters) {
        this.lens = lens;
        for (int i = 0; i < parameters.getNumberOfRays())
    }
}
