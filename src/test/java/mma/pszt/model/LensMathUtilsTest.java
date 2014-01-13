package mma.pszt.model;

import mma.pszt.utils.Line;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static mma.pszt.model.LensMathUtils.calculateIncidenceAngle;
import static mma.pszt.model.LensMathUtils.calculateIntersectingLines;


/**
 * @author Maciej Jagiello
 */
public class LensMathUtilsTest {

    static{
        PropertyConfigurator.configure("log4j.properties");
    }

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

    @Test
    public void testIncidencePointCalculation1(){
        Line l1 = Mockito.mock ( Line.class );
        Line l2 = Mockito.mock ( Line.class );
        //horizontal
        Mockito.when(l1.getA()).thenReturn(0.0);
        Mockito.when(l1.getB()).thenReturn(-1.0);
        Mockito.when(l1.getC()).thenReturn(5.0);
        //vertical
        Mockito.when(l2.getA()).thenReturn(-1.0);
        Mockito.when(l2.getB()).thenReturn(0.0);
        Mockito.when(l2.getC()).thenReturn(-1.0);
        Assert.assertEquals( 0 , calculateIncidenceAngle(l1 , l2) , 0.01 );
    }

    @Test
    public void testIncidencePointCalculation2(){
        Line l1 = Mockito.mock ( Line.class );
        Line l2 = Mockito.mock ( Line.class );

        Mockito.when(l1.getA()).thenReturn(1.0);
        Mockito.when(l1.getB()).thenReturn(1.0);
        Mockito.when(l1.getC()).thenReturn(5.0);

        Mockito.when(l2.getA()).thenReturn(1.0);
        Mockito.when(l2.getB()).thenReturn(0.0);
        Mockito.when(l2.getC()).thenReturn(4.0);
        //expected angle - 45 degrees
        Assert.assertEquals(  Math.PI / 4 , calculateIncidenceAngle(l1 , l2)  , 0.01 );
    }

    @Test
    public void testIncidencePointCalculation3(){
        Line l1 = Mockito.mock ( Line.class );
        Line l2 = Mockito.mock ( Line.class );

        Mockito.when(l1.getA()).thenReturn(-1.0);
        Mockito.when(l1.getB()).thenReturn(1.0);
        Mockito.when(l1.getC()).thenReturn(5.0);

        Mockito.when(l2.getA()).thenReturn(1.0);
        Mockito.when(l2.getB()).thenReturn(1.0);
        Mockito.when(l2.getC()).thenReturn(4.0);
        //expected angle - 90 degrees
        Assert.assertEquals(  0 , calculateIncidenceAngle(l1 , l2)  , 0.01 );
    }

    @Test
    public void testIncidencePointCalculation4(){
//        thinking about sth non-trivial...
        Line l1 = Mockito.mock ( Line.class );
        Line l2 = Mockito.mock ( Line.class );

        Mockito.when(l2.getA()).thenReturn(1.0);
        Mockito.when(l2.getB()).thenReturn(-1.0);
        Mockito.when(l2.getC()).thenReturn(3.0);

        Mockito.when(l1.getA()).thenReturn( Math.tan( Math.PI / 12));
        Mockito.when(l1.getB()).thenReturn(-1.0);
        Mockito.when(l1.getC()).thenReturn(3.0);
        //expected angle -
        Assert.assertEquals( Math.PI/3  , calculateIncidenceAngle(l1 , l2)  , 0.01 );
    }
}