package mma.pszt.model;

import lombok.Getter;
import lombok.Setter;
import mma.pszt.utils.LensMathUtils;
import mma.pszt.utils.Line;
import mma.pszt.utils.Parameters;
import mma.pszt.utils.Point;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Lens with rays, evaluated score and calculated intersection points
 *
 * @author Maciej Jagiello
 */
@Getter
public class EvaluatedLens {
    private static final Logger logger = Logger.getLogger(EvaluatedLens.class.getName());
    private final Set<Ray> rays;
    private final Lens lens;
    private final Parameters parameters;
    private List<Point> leftSidePoints;
    private List<Point> rightSidePoints;
    @Setter
    private int score = 0;

    public EvaluatedLens(final Lens lens, Parameters parameters) {
        logger.debug("Evaluated Lens with previous lens");
        this.parameters = parameters;
        this.lens = lens;
        this.rays = new HashSet<>();
        score = 0;
        leftSidePoints = evaluateLensPoints(lens.getLeftSidePoints());
        rightSidePoints = evaluateLensPoints(lens.getRightSidePoints());

        score += evaluateScore(leftSidePoints, rightSidePoints);
    }

    private int evaluateScore(List<Point> leftSidePoints, List<Point> rightSidePoints) {
        int score = 0;
        for (int i = 0; i < parameters.getNumberOfRays(); i++) {
            logger.debug("For ray i=" + i + " evaluating points.");
            List<Point> rayPoints = new ArrayList<>();
            // wygeneruj pierwszy promień (prostą) i zapisz jego punkt startowy
            Point firstPoint = new Point(-Lens.BASE_DISTANCE * 2, 5 + (i * (Lens.LENS_HEIGHT - 5)) / parameters.getNumberOfRays());
            Point lineFromFirstPoint = new Point(firstPoint.getX() + 100, firstPoint.getY());
            rayPoints.add(firstPoint);
            Line rayLine = new Line(firstPoint, lineFromFirstPoint);
            // znajdź segment soczewki z którym promień się krzyżuje
            Line lensLine = getLensLineIntersectedWithRay(rayLine, leftSidePoints);
            Point intersectionPoint = null;
            if (lensLine != null) {
                intersectionPoint = LensMathUtils.calculateIntersectingPoint(rayLine, lensLine);
            }
            if (lensLine != null && intersectionPoint != null) {
                // zapisz punkt przecięcia promienia (prostej) z którymś z lewych segmentów soczewki
                rayPoints.add(intersectionPoint);
            } else {
                score = 200000;
                continue;
            }

            // znajdź nowe równanie promienia (prostej) po załamaniu przez soczewkę
            Line midRay = null;
            try {
                midRay = LensMathUtils.getRefractedLine(
                        rayLine,
                        lensLine,
                        1.0,
                        parameters.getRefractiveIndex()
                );
            } catch (IllegalArgumentException e) {
                logger.error("Refraction probably not possible");
                Ray ray = new Ray(rayPoints);
                this.rays.add(ray);
                score = 200000;
                continue;
            }

            Line rightLensLine = getLensLineIntersectedWithRay(midRay, rightSidePoints);
            Point rightIntersectionPoint = null;
            if (rightLensLine != null) {
                rightIntersectionPoint = LensMathUtils.calculateIntersectingPoint(midRay, rightLensLine);
            }
            if (rightLensLine != null && rightIntersectionPoint != null) {
                // zapisz punkt przecięcia promienia (prostej) z którymś z lewych segmentów soczewki
                rayPoints.add(rightIntersectionPoint);
            } else {
                score = 200000;
                continue;
            }

            Line endRay;
            try {
                endRay = LensMathUtils.getRefractedLine(
                        midRay,
                        rightLensLine,
                        parameters.getRefractiveIndex(),
                        1.0
                );
            } catch (IllegalArgumentException e) {
                logger.error("Refraction probably not possible");
                Ray ray = new Ray(rayPoints);
                this.rays.add(ray);
                score = 200000;
                continue;
            }

            // zapisz ostatni punkt - koniec promienia
            assert (endRay.get(300) > 0 && endRay.get(300) < 300);
            rayPoints.add(new Point(300, endRay.get(300)));

            Ray ray = new Ray(rayPoints);
            this.rays.add(ray);
            score += Math.pow(LensMathUtils.computePointsDistance(new Point(150, Lens.LENS_HEIGHT / 2), endRay), 2);
        }
        return score;
    }

    private Line getLensLineIntersectedWithRay(Line rayLine, List<Point> lensPoints) {
        Line lensLine = null;
        Point intersectionPoint;
        for (int j = 0; j < parameters.getNumberOfPoints() - 1; j++) {
            Point lensPoint1 = lensPoints.get(j);
            Point lensPoint2 = lensPoints.get(j + 1);
            lensLine = new Line(lensPoint1, lensPoint2);
            intersectionPoint = LensMathUtils.calculateIntersectingPoint(rayLine, lensLine);
            if (isTheFirstInTheMiddleOfRemains(intersectionPoint, lensPoint1, lensPoint2)) {
                break;
            }
            lensLine = null;
        }
        return lensLine;
    }

    private boolean isTheFirstInTheMiddleOfRemains(Point intersectionPoint, Point lensPoint1, Point lensPoint2) {
        return (lensPoint1.getX() <= intersectionPoint.getX() && intersectionPoint.getX() <= lensPoint2.getX()
                || lensPoint2.getX() <= intersectionPoint.getX() && intersectionPoint.getX() <= lensPoint1.getX())
                &&
                (lensPoint1.getY() <= intersectionPoint.getY() && intersectionPoint.getY() <= lensPoint2.getY()
                        || lensPoint2.getY() <= intersectionPoint.getY() && intersectionPoint.getY() <= lensPoint1.getY());
    }

    private List<Point> evaluateLensPoints(int[] lensSegments) {
        List<Point> lensPoints = new ArrayList<>();
        for (int i = 0; i < parameters.getNumberOfPoints(); ++i) {
            lensPoints.add(new Point(lensSegments[i], (Lens.LENS_HEIGHT * i * 1.0) / parameters.getNumberOfPoints()));
        }
        return lensPoints;
    }
}
