package mma.pszt.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.util.Random;

/**
 * Class to represent single lens.
 */
@EqualsAndHashCode
public class Lens {
    private static final Logger logger = Logger.getLogger(Lens.class.getName());
    static final int BASE_DISTANCE = 30;
    @Getter
    int pointsQuantity;
    static final int LENS_HEIGHT = 200;
    @Getter
    private final int[] leftSidePoints;
    @Getter
    private final int[] rightSidePoints;
    @Getter
    private static int noGeneration;

    /**
     * Default constructor - creates flat lens.
     */
    public Lens(int pointsQuantity) {
        this.pointsQuantity = pointsQuantity;

        this.leftSidePoints = new int[pointsQuantity];
        this.rightSidePoints = new int[pointsQuantity];
        noGeneration = 0;

        for (int i = 0; i < pointsQuantity; ++i) {
            this.leftSidePoints[i] = -BASE_DISTANCE;
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
        pointsQuantity = previousLens.getPointsQuantity();
        this.rightSidePoints = new int[previousLens.getPointsQuantity()];
        this.leftSidePoints = new int[previousLens.getPointsQuantity()];

        noGeneration++;

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
}
