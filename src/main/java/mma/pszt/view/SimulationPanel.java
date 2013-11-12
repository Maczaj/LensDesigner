package mma.pszt.view;/**
 * Created with IntelliJ IDEA.
 * User: maczaj
 * Date: 09.11.13
 * Time: 17:17
 * To change this template use File | Settings | File Templates.
 */

import lombok.Setter;
import mma.pszt.model.Lens;
import mma.pszt.model.LensSegment;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Color;
import java.util.List;

public class SimulationPanel extends JPanel {
    private static final Logger logger = Logger.getLogger(SimulationPanel.class.getName());
    private static final int offset = 100;
   //TODO CAŁY WIDOK DO ZROBIENIA!!!
    @Setter private int lensToDraw;
    @Setter private List<Lens> lenses;

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Lens lens = lenses.get(lensToDraw);

        List<LensSegment> leftSegs = lens.getLeftSegments();
        List<LensSegment> rightSegs = lens.getRightSegments();

        g.setColor(Color.GRAY);

        Point left = leftSegs.get(0).getStartingPoint();
        Point right = rightSegs.get(0).getStartingPoint();

        g.drawLine( (int) left.getY(), (int) left.getX(), (int) right.getY(), (int) right.getX());

        for( LensSegment seg : leftSegs){
            Point start = seg.getStartingPoint();
            Point end = seg.getEndingPoint();

            g.drawLine( (int)start.getY() , (int) start.getX() , (int) end.getY() , (int) end.getX());
        }

        left = leftSegs.get(leftSegs.size()-1).getEndingPoint();
        right = rightSegs.get(rightSegs.size()-1).getEndingPoint();

        g.drawLine((int) left.getY() , (int) left.getX() , (int) right.getY() , (int) right.getX() );


    }

}
