package mma.pszt.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
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
    // TODO trzeba określić czym są te stałe (piksele, centymetry, chuj wie?)
    private static final int BASE_DISTANCE = 15;
    private static final int POINTS_QUANTITY = 10;
    private static final int LENS_HEIGHT = 100;
    private final Random rand = new Random(System.currentTimeMillis());

    //do rozważenia, czy punkty trzymać jako oddzielne struktury
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
     * @param otherLens    lens to mutate from
     * @param mutationRate level of mutation (must be between 0 and 1)
     */
    public Lens(final Lens otherLens, final double mutationRate, final int noGeneration) {
        if (mutationRate < 0 || mutationRate > 1) {
            throw new IllegalArgumentException("mutationRate must be between 0 and 1");
        }

        //copy points first
        this.rightSidePoints = new int[POINTS_QUANTITY];
        this.leftSidePoints = new int[POINTS_QUANTITY];

        this.noGeneration = noGeneration;

        //now mutate each point separately
        int i = 0;
        for (int point : otherLens.leftSidePoints) {                                       //how to avoid magic number?
            leftSidePoints[i] = (point + (int) (mutationRate * point * rand.nextInt(2) * 2 - 1));
            ++i;
        }
        i = 0;
        for (int point : otherLens.rightSidePoints) {                                     //how to avoid magic number?
            rightSidePoints[i] = (point + (int) (mutationRate * point * rand.nextInt(2) * 2 - 1));
            i++;
        }
    }

    /**
     * Creates new lens as combine from two lenses plus mutation.
     *
     * @param otherLens    lens to mutate from
     * @param mutationRate level of mutation (must be between 0 and 1)
     */
    public Lens(final Lens lens1, final Lens lens2, final double mutationRate, final int noGeneration) {
        if (mutationRate < 0 || mutationRate > 1) {
            throw new IllegalArgumentException("mutationRate must be between 0 and 1");
        }

        //copy points first
        this.rightSidePoints = new int[POINTS_QUANTITY];
        this.leftSidePoints = new int[POINTS_QUANTITY];

        this.noGeneration = noGeneration;

        //now mutate each point separately
        int i = 0;
        for (int point : otherLens.leftSidePoints) {                                       //how to avoid magic number?
            leftSidePoints[i] = (point + (int) (mutationRate * point * rand.nextInt(2) * 2 - 1));
            ++i;
        }
        i = 0;
        for (int point : otherLens.rightSidePoints) {                                     //how to avoid magic number?
            rightSidePoints[i] = (point + (int) (mutationRate * point * rand.nextInt(2) * 2 - 1));
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

    /**
     * @return list of lens left-side segments.
     */
    public List<LensSegment> getLeftSegments() {
        return getSegments(leftSidePoints);
    }

    /**
     * @return list of lens right-side segments.
     */
    public List<LensSegment> getRightSegments() {
        return getSegments(rightSidePoints);
    }

    public int getScore() {

        return -1;
    }
}
