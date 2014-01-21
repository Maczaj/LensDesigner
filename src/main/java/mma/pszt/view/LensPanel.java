package mma.pszt.view;

import mma.pszt.model.EvaluatedLens;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.LinkedList;


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
        logger.debug(lens == null);
        System.out.println("to jest do namalowania: " + lens.getIntersectionPoints().size());
        g.drawLine( 0, 0, 100, 100);


    }

    public void setLens(EvaluatedLens lens)
    {
        this.lens = lens;
    }

}
