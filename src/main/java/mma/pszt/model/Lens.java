package mma.pszt.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import mma.pszt.utils.Point;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to represent single lens.
 */
@EqualsAndHashCode
public class Lens {
    private static final Logger logger = Logger.getLogger(Lens.class.getName());
    static final int BASE_DISTANCE = 15;
    static final int POINTS_QUANTITY = 10;
    static final int LENS_HEIGHT = 100;
    @Getter
    private final int[] leftSidePoints;
    @Getter
    private final int[] rightSidePoints;
    @Getter
    private int noGeneration;

    /**
     * Default constructor - creates flat lens.
     */
    public Lens() {
        this.leftSidePoints = new int[POINTS_QUANTITY];
        this.rightSidePoints = new int[POINTS_QUANTITY];
        noGeneration = 0;

        for (int i = 0; i < POINTS_QUANTITY; ++i) {
            this.leftSidePoints[i] = BASE_DISTANCE;
            this.rightSidePoints[i] = BASE_DISTANCE;
        }
    }

    /**
     * Creates new lens as mutated referenced lens.
     *
     * @param previousLens lens to mutate from
     * @param sigma
     */
    public Lens(final Lens previousLens, final double sigma) {
        //copy points first
        this.rightSidePoints = new int[POINTS_QUANTITY];
        this.leftSidePoints = new int[POINTS_QUANTITY];

        this.noGeneration = previousLens.getNoGeneration() + 1;

        //now mutate each point separately
        int i = 0;
        Random rand = new Random();
        for (int point : previousLens.leftSidePoints) {
            leftSidePoints[i] = (point + (int) (sigma * rand.nextGaussian()));
            ++i;
        }
        i = 0;
        for (int point : previousLens.rightSidePoints) {
            rightSidePoints[i] = (point + (int) (sigma * rand.nextGaussian()));
            i++;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Lens (generation: " + noGeneration + "): ");
        for (Integer point : leftSidePoints) {
            sb.append(point + " ");
        }
        sb.append("\\ ");
        for (Integer point : rightSidePoints) {
            sb.append(point + " ");
        }
        return sb.toString();
    }

    private List<LensSegment> getSegments(final int[] side) {
        List<LensSegment> lst = new ArrayList<LensSegment>();
        double stepSize = (double) LENS_HEIGHT / (double) POINTS_QUANTITY;

        for (int i = 0; i < side.length - 1; ++i) {
            Point first = new Point(side[i], (int) (i * stepSize));
            Point second = new Point(side[i + 1], (int) ((i + 1) * stepSize));

            lst.add(new LensSegment(first, second));
        }
        return lst;
    }

//    /**
//     * @return list of lens left-side segments.
//     */
//    public List<LensSegment> getLeftSegments() {
//        return getSegments(leftSidePoints);
//    }
//
//    /**
//     * @return list of lens right-side segments.
//     */
//    public List<LensSegment> getRightSegments() {
//        return getSegments(rightSidePoints);
//    }
}
