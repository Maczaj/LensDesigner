package mma.pszt.model;/**
 * Created with IntelliJ IDEA.
 * User: maczaj
 * Date: 09.11.13
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */

import mma.pszt.utils.Line;
import org.apache.log4j.Logger;

import java.awt.*;


/**
 * Class to represent single segment of Lens.
 */
public class LensSegment {
    private static final Logger logger = Logger.getLogger(LensSegment.class.getName());

    private Point startingPoint;
    private Point endingPoint;

    public LensSegment(Point first, Point second){
        this.startingPoint = first;
        this.endingPoint = second;
        logger.debug("Creating segment - from " + startingPoint + " to " + endingPoint);
    }
    /**
     * @return beginning point of this segment.
     */
    public Point getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    /**
     * @return ending point of this segment.
     */
    public Point getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(Point endingPoint) {
        this.endingPoint = endingPoint;
    }

    public Line getAsLineEquation(){
         return new Line(this.startingPoint , this.endingPoint);
    }

    @Override
    public String toString() {
        return  startingPoint + " => " + endingPoint ;
    }
}
