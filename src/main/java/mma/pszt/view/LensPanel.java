package mma.pszt.view;

import mma.pszt.model.EvaluatedLens;
import mma.pszt.model.Ray;
import mma.pszt.utils.*;
import mma.pszt.utils.Point;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class LensPanel extends JPanel {
    private static final Logger logger = Logger.getLogger(LensPanel.class.getName());

    private int numberLensToPaint;

    private EvaluatedLens lens;

    public LensPanel(EvaluatedLens lens)
    {
        this.lens = lens;
    }


    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);


        this.setBackground(Color.BLACK);

        g.setColor(Color.GRAY);
        System.out.println("to jest do namalowania");
        for (Ray r : lens.getRays()) {
            for (int i = 0; i < r.getMidPoints().size() - 1; ++i) {
//                logger.debug("draw ray " + i);
                g.setColor(new Color(192,0,0));
                g.drawLine(
                        (int)r.getMidPoints().get(i).getX() * View.SCALE + View.OFFSET_X,
                        (int)r.getMidPoints().get(i).getY() * View.SCALE + View.OFFSET_Y,
                        (int)r.getMidPoints().get(i+1).getX() * View.SCALE + View.OFFSET_X,
                        (int)r.getMidPoints().get(i+1).getY() * View.SCALE + View.OFFSET_Y
                );
            }
        }

        for (int i = 0; i < lens.getLeftSidePoints().size() - 1; i++) {
            Point p = lens.getLeftSidePoints().get(i);
            Point p2 = lens.getLeftSidePoints().get(i + 1);
//            logger.debug("draw point left: " + p);
            Color col = new Color(0, (i%2 == 0 ? 255 : 0), (i%2 != 0 ? 255 : 0));
            g.setColor(col);
            g.drawLine(
                    (int) p.getX() * View.SCALE + View.OFFSET_X,
                    (int) p.getY() * View.SCALE + View.OFFSET_Y,
                    (int) p2.getX() * View.SCALE + View.OFFSET_X,
                    (int) p2.getY() * View.SCALE + View.OFFSET_Y
            );
        }

        for (int i = 0; i < lens.getRightSidePoints().size() - 1; i++) {
            Point p = lens.getRightSidePoints().get(i);
            Point p2 = lens.getRightSidePoints().get(i + 1);
//            logger.debug("draw point right: " + p);
            g.drawLine(
                    (int) p.getX() * View.SCALE + View.OFFSET_X,
                    (int) p.getY() * View.SCALE + View.OFFSET_Y,
                    (int) p2.getX() * View.SCALE + View.OFFSET_X,
                    (int) p2.getY() * View.SCALE + View.OFFSET_Y
            );
        }
    }

    public void setLens(EvaluatedLens lens)
    {
        this.lens = lens;
    }

}
