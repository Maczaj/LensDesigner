package mma.pszt.view;

import mma.pszt.model.EvaluatedLens;

import javax.swing.*;

class LensFrame extends JFrame {
    private static final long serialVersionUID = -1005384575696676214L;

    private final LensPanel toCoJest;

    private EvaluatedLens lens;

    public LensFrame(final EvaluatedLens lens) {
        this.lens = lens;
        setSize(700 + 20, 500 + 40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Interactive lens designer");

        toCoJest = new LensPanel(this.lens);

        this.add(toCoJest);
    }

    void repaintLens() {
        toCoJest.repaint();
    }

    public void setLens(EvaluatedLens lens) {
        toCoJest.setLens(lens);
    }
}
