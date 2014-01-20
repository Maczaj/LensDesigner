package mma.pszt.utils;/**
 * Created with IntelliJ IDEA.
 * User: maczaj
 * Date: 09.11.13
 * Time: 17:46
 * To change this template use File | Settings | File Templates.
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mma.pszt.utils.Point;
import org.apache.log4j.Logger;

/**
 * Class to represent a line.
 */
@AllArgsConstructor
@Getter
@Setter
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

    /**
     * Creates new line based on given point and factors of normal vector.
     * @param p point which is contained by line
     * @param A x factor od normal vector
     * @param B y factor of normal vector
     */
    public Line( Point p , double A, double B){
        logger.debug("Creating new line with entry: Point=" + p.toString() + " a=" + A + " B=" + B);
        this.a = A;
        this.b = B;
        this.c = -A*p.getX() - B*p.getY();
        logger.debug("Got line " + this.toString());
    }

    /**
     * Constructor creating copy of line. (not working)
     * @param l line to be copied
     */
    public Line( Line l ){
        this.a = l.a;
        this.b = l.b;
        this.c = l.c;
    }

    /**
     * Creates new line with scaled factors.
     * @param p level of scaling
     * @return
     */
    public void scaleFactors ( double p ) {
        logger.debug("Scaling for: " + this.toString() +" with parameter " + p);

        this.a *= p;
        this.b *= p;
        this.c *= p;
        logger.debug("After scaling: " + this.toString());
    }

    @Override
    public String toString() {
        return "Line{" +
                a + "x+" +
                b + "y+" +
                +c + "=0}";
    }
}
