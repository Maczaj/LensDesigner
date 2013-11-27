package mma.pszt.model;

import mma.pszt.utils.Line;
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
            x = -((c2/a2)+b2*(a1*c2-a2*c1)/(a2*(a2*b1-a1*b2)));
            y = (a1*c2-a2*c1)/(a2*b1-a1*b2);
        } else {
            x = -((c1/a1)+b1*(a2*c1-a1*c2)/(a1*(a1*b2-a2*b1)));
            y = (a2*c1-a1*c2)/(a1*b2-a2*b1);
        }

        return new Point(x, y);
    }
}
