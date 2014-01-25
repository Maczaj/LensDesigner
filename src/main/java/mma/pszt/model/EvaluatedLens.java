package mma.pszt.model;

import lombok.Getter;
import lombok.Setter;
import mma.pszt.utils.Line;
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
    private List<Point> leftSidePoints;
    private List<Point> rightSidePoints;
    @Setter
    private int score = 0;

    public EvaluatedLens(final Lens lens, Parameters parameters) {
        System.out.println("Evaluated Lens with previous lens");
        System.out.println(parameters);
        this.lens = lens;
        this.rays = new HashSet<>();
        leftSidePoints = evaluateLensPoints(lens.getLeftSidePoints());
        rightSidePoints = evaluateLensPoints(lens.getRightSidePoints());
        for (int i = 0; i < parameters.getNumberOfRays(); i++) {
            List rayPoints = new ArrayList<>();
            // wygeneruj pierwszy promień (prostą) i zapisz jego punkt startowy
            Point firstPoint = new Point( - Lens.BASE_DISTANCE * 2, (i * Lens.LENS_HEIGHT) / parameters.getNumberOfRays());
            Point lineFromFirstPoint = new Point(firstPoint.getX() + 1, firstPoint.getY());
            rayPoints.add(firstPoint);
            Line rayLine =  new Line(firstPoint, lineFromFirstPoint);
            // znajdź segment soczewki z którym promień się krzyżuje
            for (int j = 0; j < Lens.POINTS_QUANTITY - 1; j++) {
                Point lensPoint1 = leftSidePoints.get(j);
                Point lensPoint2 = leftSidePoints.get(j+1);
                Line lensLine = new Line(lensPoint1, lensPoint2);
                Point intersectionPoint = LensMathUtils.calculateIntersectingPoint(rayLine, lensLine);
                if (
                        (lensPoint1.getX() < intersectionPoint.getX() && intersectionPoint.getX() < lensPoint2.getX()
                        || lensPoint2.getX() < intersectionPoint.getX() && intersectionPoint.getX() < lensPoint1.getX())
                        &&
                        (lensPoint1.getY() < intersectionPoint.getY() && intersectionPoint.getY() < lensPoint2.getY()
                        || lensPoint2.getY() < intersectionPoint.getY() && intersectionPoint.getY() < lensPoint1.getY())
                ) {
                    // zapisz punkt przecięcia promienia (prostej) z którymś z lewych segmentów soczewki;
                    rayPoints.add(intersectionPoint);
                    break;
                }
            }
            Line lensLine = new Line(leftSidePoints.get(0), leftSidePoints.get(1));
            Line newLine = LensMathUtils.getRefractedLine(
                    rayLine,
                    lensLine,
                    1.0,
                    parameters.getRefractiveIndex()
            );
            rayPoints.add(
                    new Point(
                            LensMathUtils.calculateIntersectingPoint(rayLine, lensLine).getX()+10,
                            newLine.get(LensMathUtils.calculateIntersectingPoint(rayLine, lensLine).getX() + 10)
                    )
            );

            // znajdź nowe równanie promienia (prostej) po załamaniu przez soczewkę
            // powtórz dwa poprzednie punkty dla drugiej części soczewki
            // zapisz ostatni punkt - koniec promienia

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

    private List<Point> evaluateLensPoints(int [] lensSegments) {
        List<Point> lensPoints = new ArrayList<>();
        for (int i = 0; i < Lens.POINTS_QUANTITY; ++i) {
            lensPoints.add(new Point(lensSegments[i], (Lens.LENS_HEIGHT * i * 1.0) / Lens.POINTS_QUANTITY ));
        }
        return lensPoints;
    }
}
