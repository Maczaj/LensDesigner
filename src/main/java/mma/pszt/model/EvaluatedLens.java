package mma.pszt.model;

import lombok.Getter;
import mma.pszt.utils.Point;

import java.util.Set;

/**
 * @author Maciej Jagiello
 */
@Getter
public class EvaluatedLens {

    private final Set<Ray> rays;
    private final Lens lens;
    private Set<Point> intersectionPoints;
    public EvaluatedLens(final Lens lens, final Set<Ray> rays, final int score) {
        this.lens = lens;
        this.rays = rays;
    }

}
