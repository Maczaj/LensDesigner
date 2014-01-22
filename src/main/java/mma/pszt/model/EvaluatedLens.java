package mma.pszt.model;

import lombok.Getter;
import lombok.Setter;
import mma.pszt.utils.Parameters;
import mma.pszt.utils.Point;
import sun.util.logging.resources.logging;

import java.util.*;

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
        System.out.println("Evaluated Lens with previous lens");
        System.out.println(parameters);
        this.lens = lens;
        this.rays = new HashSet<>();
        for (int i = 0; i < parameters.getNumberOfRays(); i++) {
            List rayPoints = new ArrayList<>();
            rayPoints.add(new Point(-Lens.BASE_DISTANCE * 2, (i * Lens.LENS_HEIGHT) / parameters.getNumberOfRays()));
            rayPoints.add(new Point(Lens.BASE_DISTANCE * 2, (i * Lens.LENS_HEIGHT) / parameters.getNumberOfRays()));
            System.out.println("next ray add " + ((i * Lens.LENS_HEIGHT) / parameters.getNumberOfRays()));
//            rayPoints.add(new Point( , ));
//            rayPoints.add(new Point( , ));
//            rayPoints.add(new Point( , ));
            Ray ray = new Ray(rayPoints);
            this.rays.add(ray);
        }
    }

    public EvaluatedLens(Parameters parameters) {
        System.out.println("Evaluated Lens with new lens");
        this.lens = new Lens();
        this.rays = new HashSet<Ray>();
//        this.rays.add(new Ray());
//        this.intersectionPoints = new HashSet<Point>();
//        this.intersectionPoints.add(new Point(3.0,4.0));
//        this.intersectionPoints.add(new Point(6.0,-4.0));
//        this.intersectionPoints.add(new Point(-2.0,-4.0));
        for (int i = 0; i < parameters.getNumberOfRays(); i++) {
            List rayPoints = new ArrayList<>();
            rayPoints.add(new Point(-Lens.BASE_DISTANCE * 2, (i * Lens.LENS_HEIGHT) / parameters.getNumberOfRays()));
            rayPoints.add(new Point(+Lens.BASE_DISTANCE * 2, (i * Lens.LENS_HEIGHT) / parameters.getNumberOfRays()));
//            rayPoints.add(new Point( , ));
//            rayPoints.add(new Point( , ));
//            rayPoints.add(new Point( , ));
            Ray ray = new Ray(rayPoints);
            this.rays.add(ray);
        }
    }
}
