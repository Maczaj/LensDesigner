package mma.pszt.view;

import mma.pszt.model.EvaluatedLens;
import mma.pszt.utils.Parameters;

import java.util.LinkedList;

/**
 *@author Arkadiusz Szlachetka
 */
public class View
{
    static int OFFSET_X = 200;
    static int OFFSET_Y = 20;
    static int SCALE = 4;
    private LensFrame lensFrame;
    EvaluatedLens lens;

    public View()
    {
    }

    public void createFrame(EvaluatedLens lens)
    {
        this.lens = lens;
        lensFrame = new LensFrame(lens);
        lensFrame.setVisible(true);

    }
    public Parameters setParameters() {
        ParamsFrame paramsFrame = new ParamsFrame();

        return paramsFrame.getParam();
    }

    public void setLens(EvaluatedLens lens) {
        lensFrame.setLens(lens);
    }

    public void drawView() {
        lensFrame.repaintLens();
    }

}
