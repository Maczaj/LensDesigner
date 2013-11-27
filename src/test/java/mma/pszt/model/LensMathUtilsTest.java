package mma.pszt.model;

import mma.pszt.utils.Line;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static mma.pszt.model.LensMathUtils.calculateIntersectingLines;


/**
 * @author Maciej Jagiello
 */
public class LensMathUtilsTest {

    @Test
    public void testTrivial(){
        Line l1 = Mockito.mock(Line.class);
        Line l2 = Mockito.mock(Line.class);
        Mockito.when(l1.getA()).thenReturn(1.0);
        Mockito.when(l1.getB()).thenReturn(-1.0);
        Mockito.when(l1.getC()).thenReturn(0.0);
        Mockito.when(l2.getA()).thenReturn(0.5);
        Mockito.when(l2.getB()).thenReturn(-1.0);
        Mockito.when(l2.getC()).thenReturn(0.0);
        Assert.assertEquals(calculateIntersectingLines(l1, l2), new Point(0, 0));
    }

    @Test
    public void testNonTrivial(){
        Line l1 = Mockito.mock(Line.class);
        Line l2 = Mockito.mock(Line.class);
        Mockito.when(l1.getA()).thenReturn(1.0);
        Mockito.when(l1.getB()).thenReturn(0.0);
        Mockito.when(l1.getC()).thenReturn(-1.0);
        Mockito.when(l2.getA()).thenReturn(0.0);
        Mockito.when(l2.getB()).thenReturn(-1.0);
        Mockito.when(l2.getC()).thenReturn(0.0);
        Assert.assertEquals(calculateIntersectingLines(l1, l2), new Point(1, 0));
    }

    @Test
    public void testNonTrivial2(){
        Line l1 = Mockito.mock(Line.class);
        Line l2 = Mockito.mock(Line.class);
        Mockito.when(l1.getA()).thenReturn(0.5);
        Mockito.when(l1.getB()).thenReturn(-1.0);
        Mockito.when(l1.getC()).thenReturn(0.0);
        Mockito.when(l2.getA()).thenReturn(-1.0);
        Mockito.when(l2.getB()).thenReturn(-1.0);
        Mockito.when(l2.getC()).thenReturn(2.0);
        Point one = calculateIntersectingLines(l1, l2);
        Point two = new Point(4.0/3, 2.0/3);
        Assert.assertTrue(pointsEqualsPlusMinus(one, two, 0.01));
    }

    private boolean pointsEqualsPlusMinus(Point p1, Point p2, double precision) {
        return (p1.getX() >= p2.getX()-precision)
            && (p1.getX() <= p2.getX()+precision)
            && (p1.getY() >= p2.getY()-precision)
            && (p1.getY() <= p2.getY()+precision);
    }
}
