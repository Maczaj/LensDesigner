package mma.pszt.utils;/**
 * Created with IntelliJ IDEA.
 * User: maczaj
 * Date: 09.11.13
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.log4j.Logger;

import java.awt.*;

/**
 * Class to represent a line.
 */
@AllArgsConstructor
@Getter
public class Line {
    private static final Logger logger = Logger.getLogger(Line.class.getName());

    private double a;
    private double b;
    private double c;

    /**
     * Crates new line based on two points.
     * @param p1 starting point
     * @param p2 ending point
     */
    public Line(Point p1 , Point p2){

        //this means angle with OX is 90 degrees
        if(p2.getX() == p1.getX()){
            this.a = 1;
            this.b = 0;
            this.c = -p2.getX();
        }
        else{
            this.a = (double)(p1.getY()-p2.getY()) / (p1.getX() - p2.getX());
            this.b = -1;
            this.c = p2.getY() - this.a * p2.getX();
        }
    }

    @Override
    public String toString() {
        return "Line{" +
                a + "x+" +
                b + "y+" +
                +c + "=0}";
    }
}
