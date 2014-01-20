package mma.pszt.model;

import mma.pszt.utils.Line;
import mma.pszt.utils.Point;
import org.apache.log4j.Logger;

/**
 * @author Maciej Jagiello
 */
abstract class LensMathUtils {

    private static final Logger logger = Logger.getLogger(Lens.class.getName());

    private LensMathUtils() throws Exception {
        throw new IllegalAccessException("Abstract class should not be instanced.");
    }

    public static Point calculateIntersectingLines(Line one, Line two) {
        double a1 = one.getA();
        double b1 = one.getB();
        double c1 = one.getC();
        double a2 = two.getA();
        double b2 = two.getB();
        double c2 = two.getC();

        // two lines are parallel OR equal
        if (a1 == a2 && b1 == b2 || a1 == a2 && b1 == b2 && c1 == c2) {
            return null;
        }
        double x;
        double y;
        // the same way to calculate intersect point, but without dividing by zero
        if (a2 != 0) {
            x = -((c2 / a2) + b2 * (a1 * c2 - a2 * c1) / (a2 * (a2 * b1 - a1 * b2)));
            y = (a1 * c2 - a2 * c1) / (a2 * b1 - a1 * b2);
        } else {
            x = -((c1 / a1) + b1 * (a2 * c1 - a1 * c2) / (a1 * (a1 * b2 - a2 * b1)));
            y = (a2 * c1 - a1 * c2) / (a1 * b2 - a2 * b1);
        }

        return new Point(x, y);
    }

    /**
     *
     * @param l1 line which is inciding
     * @param l2
     * @return angle in radians
     */
    public static double calculateIncidenceAngle(Line l1, Line l2){
        logger.debug("Attempting to caltulate incidence angle " + l1.toString() + " => " + l2.toString());

        if( ( l1.getA()*l2.getB() - l2.getA()*l1.getB() ) == 0){
            throw new IllegalArgumentException("Lines are parallel!");
        }
        if( ( l1.getA()*l2.getA() + l1.getB()*l2.getB() ) == 0){
            return 0.0;
        }

        Line normal = new Line ( calculateIntersectingLines( l1 , l2 ) , -l2.getB() , l2.getA() );

      return Math.abs( Math.atan(  ( l1.getA()*normal.getB() - normal.getA() * l1.getB() ) / ( l1.getA() * normal.getA() + l1.getB() * normal.getB()) ) );
    }

    /**
     * Calculates angle of refraction based on angle of incidence.
     * @param incidenceAngle
     * @param nSourceEnv refractive index of source environment.
     * @param nDestinationEnv refractive index of destination environment.
     * @return computed value of angle
     */
    public static double calculateRefractionAngle( double incidenceAngle , double nSourceEnv , double nDestinationEnv  ){
        return Math.asin( ( nDestinationEnv * Math.sin ( incidenceAngle ) ) / nSourceEnv );
    }

    /**
     * Calculates equation of refracted line based on refraction angle and point of refraction.
     * @param refractionAngle angle of refraction in radians
     * @param refractionPoint point contained by refracted line
     * @return refracted line
     */
    public static Line calculateRefractedLine(double refractionAngle, Point refractionPoint){
        double a = Math.tan( refractionAngle + Math.PI/2 );
        double b = -1.0;
        double c = a * refractionPoint.getY() - b * refractionPoint.getY();

        return new Line(a , b ,c );
    }


}
