package mma.pszt.view;

import mma.pszt.model.EvaluatedLens;
import mma.pszt.model.Ray;
import mma.pszt.utils.Point;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LensPanel extends JPanel {
    private static final Logger logger = Logger.getLogger(LensPanel.class.getName());

    JPanel jPanel = new JPanel();
    JButton buttonPause = new JButton("Pause!");

    JPanel jPanelTextFieldOne = new JPanel(new FlowLayout());
    JLabel l1 = new JLabel("Generation: 0");

    private EvaluatedLens lens;

    public LensPanel(EvaluatedLens lens) {
        this.lens = lens;
        jPanel.setBackground(Color.black);

        jPanelTextFieldOne.setBackground(Color.black);
        l1.setForeground(Color.WHITE);
        jPanelTextFieldOne.add(l1);

        jPanel.add(buttonPause);
        this.add(jPanel);
        this.add(jPanelTextFieldOne);

        buttonPause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Waiter.getInstance().changeWait();
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        this.setBackground(Color.BLACK);

        l1.setText("Generation: " + lens.getLens().getNoGeneration());

        g.setColor(new Color(192, 0, 0));
        for (Ray r : lens.getRays()) {
            for (int i = 0; i < r.getMidPoints().size() - 1; ++i) {
//                logger.debug("draw ray " + i);
                g.drawLine(
                        (int) r.getMidPoints().get(i).getX() * View.SCALE + View.OFFSET_X,
                        (int) r.getMidPoints().get(i).getY() * View.SCALE + View.OFFSET_Y,
                        (int) r.getMidPoints().get(i + 1).getX() * View.SCALE + View.OFFSET_X,
                        (int) r.getMidPoints().get(i + 1).getY() * View.SCALE + View.OFFSET_Y
                );
            }
        }

        g.setColor(new Color(94, 255, 0));
        for (int i = 0; i < lens.getLeftSidePoints().size() - 1; i++) {
            Point p = lens.getLeftSidePoints().get(i);
            Point p2 = lens.getLeftSidePoints().get(i + 1);
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
            g.drawLine(
                    (int) p.getX() * View.SCALE + View.OFFSET_X,
                    (int) p.getY() * View.SCALE + View.OFFSET_Y,
                    (int) p2.getX() * View.SCALE + View.OFFSET_X,
                    (int) p2.getY() * View.SCALE + View.OFFSET_Y
            );
        }
    }

    public void setLens(EvaluatedLens lens) {
        this.lens = lens;
    }
}
