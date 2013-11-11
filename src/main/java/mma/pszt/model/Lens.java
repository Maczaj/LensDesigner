package mma.pszt.model;/**
 * Created with IntelliJ IDEA.
 * User: maczaj
 * Date: 09.11.13
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */

import lombok.Getter;
import lombok.val;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class to represent single lens.
 */
public class Lens {
    private static final Logger logger = Logger.getLogger(Lens.class.getName());
    // TODO trzeba określić czym są te stałe (piksele, centymetry, chuj wie?)
    private static final int BASE_DISTANCE = 15;
    private static final int POINTS_QUANTITY = 10;
    private static final int LENS_HEIGH = 100;
    private static final int[] VALUES = {-1, 1} ;

    //will be used many times, so static
    private static final Random rand = new Random(System.currentTimeMillis());

    //do rozważenia, czy punkty trzymać jako oddzielne struktury
    @Getter private int[] leftSidePoints;
    @Getter private int[] rightSidePoints;

    /**
     * Default constructor - creates flat lens.
     */
    public Lens(){
        this.leftSidePoints = new int[POINTS_QUANTITY];
        this.rightSidePoints = new int[POINTS_QUANTITY];

        for(int i = 0 ; i < POINTS_QUANTITY ; ++i){
            this.leftSidePoints[i] = BASE_DISTANCE;
            this.rightSidePoints[i] = BASE_DISTANCE;
        }
    }

    /**
     * Creates new lens as mutated referenced lens.
     * @param otherLens lens to mutate from
     * @param mutationRate level of mutation (must be between 0 and 1)
     */
    public Lens(final Lens otherLens , double mutationRate){
        if(mutationRate < 0 || mutationRate > 1){
            throw new IllegalArgumentException("mutationRate must be between 0 and 1");
        }

        //copy points first
        this.rightSidePoints = new int[POINTS_QUANTITY];
        this.leftSidePoints = new int[POINTS_QUANTITY];

        //now mutate each point separately
        int i = 0;
        for(int point : otherLens.leftSidePoints){                                       //how to avoid magic number?
           leftSidePoints[i] = (point + (int)(mutationRate * point * VALUES[rand.nextInt(2)]));
            ++i;
        }
        i = 0;
        for(int point : otherLens.rightSidePoints){                                     //how to avoid magic number?
            rightSidePoints[i] = (point + (int)(mutationRate * point * VALUES[rand.nextInt(2)]));
            i++;
        }
    }

        @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append("Lens: ");
        for(Integer point : leftSidePoints){
            sb.append(point+" ");
        }

        sb.append("\\ ");

        for(Integer point : rightSidePoints){
            sb.append(point + " ");
        }

        return sb.toString();
    }

    /**
     * @return list of lens left-side segments.
     */
    public final List<LensSegment> getLeftSegments(){
        List<LensSegment> lst = new ArrayList<LensSegment>();
        double stepSize = (double) LENS_HEIGH / (double) POINTS_QUANTITY ;

        for (int i = 0 ; i < this.leftSidePoints.length - 1 ; ++i){
            Point first = new Point(leftSidePoints[i] , (int) (i*stepSize) );
            Point second = new Point(leftSidePoints[i+1] , (int) ((i+1)*stepSize));

            lst.add(new LensSegment(first, second));
        }
        return lst;
    }

    /**
     * @return list of lens right-side segments.
     */
    public final List<LensSegment> getRightSegments(){
        List<LensSegment> lst = new ArrayList<LensSegment>();
        double stepSize = (double) LENS_HEIGH / (double) POINTS_QUANTITY ;

        for (int i = 0 ; i < this.rightSidePoints.length - 1 ; ++i){
            Point first = new Point(rightSidePoints[i] , (int) (i*stepSize) );
            Point second = new Point(rightSidePoints[i+1] , (int) ((i+1)*stepSize));

            lst.add(new LensSegment(first, second));
        }
        return lst;
    }
}
