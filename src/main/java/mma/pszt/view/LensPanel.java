package mma.pszt.view;

import mma.pszt.model.EvaluatedLens;
import mma.pszt.model.Ray;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;


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
                logger.debug("draw ray " + i);
                g.drawLine(
                        (int)r.getMidPoints().get(i).getX(),
                        (int)r.getMidPoints().get(i).getY(),
                        (int)r.getMidPoints().get(i+1).getX(),
                        (int)r.getMidPoints().get(i+1).getY()
                );
            }
        }
//        g.drawLine( 0, 0, 100, 100);


    }

    public void setLens(EvaluatedLens lens)
    {
        this.lens = lens;
    }

}
