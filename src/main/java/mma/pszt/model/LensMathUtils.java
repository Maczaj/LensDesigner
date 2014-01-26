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

    /**
     * Calculates intersectiong point from two lines
     * @param lineOne
     * @param lineTwo
     * @return Point if succeed or null if lines are parallel or equal
     */
    public static Point calculateIntersectingPoint(Line lineOne, Line lineTwo) {
        double a1 = lineOne.getA();
        double b1 = lineOne.getB();
        double c1 = lineOne.getC();
        double a2 = lineTwo.getA();
        double b2 = lineTwo.getB();
        double c2 = lineTwo.getC();

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

        Line normal = new Line ( calculateIntersectingPoint(l1, l2) , -l2.getB() , l2.getA() );
        double result = ( Math.atan(  ( l1.getA()*normal.getB() - normal.getA() * l1.getB() ) / ( l1.getA() * normal.getA() + l1.getB() * normal.getB()) ) );
        logger.debug("calculateIncidenceAngle = " + result);
      return result;
    }

    /**
     * Calculates angle of refraction based on angle of incidence.
     * @param incidenceAngle
     * @param nSourceEnv refractive index of source environment.
     * @param nDestinationEnv refractive index of destination environment.
     * @return computed value of angle
     */
    public static double calculateRefractionAngle( double incidenceAngle , double nSourceEnv , double nDestinationEnv ) {
        logger.debug("Call for: " + incidenceAngle + " " + nSourceEnv + " " + nDestinationEnv);
//        double refractionAngle = Math.asin( ( nSourceEnv * Math.sin ( incidenceAngle ) ) / nDestinationEnv );
        double refractionAngle = Math.asin( ( nDestinationEnv * Math.sin ( incidenceAngle ) ) / nSourceEnv );
        return refractionAngle;
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

    /**
     * Computes distance of specified point to specified line.
     * @param point
     * @param line
     * @return distance
     */
    public static double computePointsDistance(Point point , Line line){
        return Math.abs( line.getA() * point.getX() + line.getB() * point.getY() + line.getC()) /
                Math.sqrt( Math.pow(line.getA() , 2) + Math.pow( line.getB() , 2 ));
    }

    /**
     * * Get the equation of refracted line based on two lines and refractive indexes of both environments.
     * @param l1 line which is inciding
     * @param l2 line which is considered to be an edge
     * @param nEnvSource refractive index of source environment
     * @param nEnvDest refractive index of destination environment
     * @return refracted line
     * @throws IllegalArgumentException when total internal incidence occures.
     */
    public static Line getRefractedLine(Line l1, Line l2, double nEnvSource, double nEnvDest) throws IllegalArgumentException {
        logger.debug("Calculatin refracted line for entry:" + l1.toString() + " " + l2.toString() + " sourceEnv:" + nEnvSource + " destEnv:" + nEnvDest);

        double incidenceAngle = calculateIncidenceAngle(l1 , l2);

        logger.debug("Calculated incidence angle:" + incidenceAngle);
        if( Math.abs(incidenceAngle) < 0.01){
            return l1;
        }

        double refrationAngle = calculateRefractionAngle(incidenceAngle , nEnvSource , nEnvDest );

        if( refrationAngle == Double.NaN || Double.isNaN(refrationAngle)){
            logger.error("Expcetion damn...");
//            System.exit(-2);
            throw new IllegalArgumentException("Total internal incidence occured!");
        }

        logger.debug("Calculated refraction angle:" + refrationAngle);
        Point refractionPoint = calculateIntersectingPoint(l1, l2 );
        logger.debug("Calculated intersection point:" + refractionPoint.toString());

        if(l1.getB()!=0){
            l1.scaleFactors( -1.0 / l1.getB());
        }

        double gamma = Math.atan( l1.getA() );

        double sigma = gamma - (incidenceAngle - refrationAngle);

    logger.debug("Angles: alfa=" + incidenceAngle + ", beta=" + refrationAngle + ", gamma=" + gamma + ", sigma=" + sigma );
        double a = Math.tan( sigma );
        double b = -1.0;
        double c = - a * refractionPoint.getX() - b * refractionPoint.getY();
    logger.debug("a=" + a + ", b=" + b + ", c=" + c);
        return new Line(a , b ,c );
    }
}
