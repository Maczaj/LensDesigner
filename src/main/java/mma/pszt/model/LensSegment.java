package mma.pszt.model;/**
 * Created with IntelliJ IDEA.
 * User: maczaj
 * Date: 09.11.13
 * Time: 16:24
 * To change this template use File | Settings | File Templates.
 */

import lombok.Getter;
import lombok.Setter;
import mma.pszt.utils.Line;
import org.apache.log4j.Logger;

import java.awt.*;


/**
 * Class to represent single segment of Lens.
 */
@Getter
@Setter
public class LensSegment {
    private static final Logger logger = Logger.getLogger(LensSegment.class.getName());

    private Point startingPoint;
    private Point endingPoint;

    public LensSegment(Point first, Point second){
        this.startingPoint = first;
        this.endingPoint = second;
        logger.debug("Creating segment - from " + startingPoint + " to " + endingPoint);
    }


    public Line getAsLineEquation(){
         return new Line(this.startingPoint , this.endingPoint);
    }

    @Override
    public String toString() {
        return  startingPoint + " => " + endingPoint ;
    }
}
