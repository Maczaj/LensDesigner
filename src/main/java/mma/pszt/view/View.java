package mma.pszt.view;

import mma.pszt.model.Lens;
import mma.pszt.utils.Parameters;

import java.util.List;

/**
 *@author Arkadiusz Szlachetka
 */
public class View
{

    private final LensFrame lensFrame;

    public View()
    {
        lensFrame = new LensFrame();
        lensFrame.setVisible(false);
    }

    public Parameters setParameters() {
        ParamsFrame paramsFrame = new ParamsFrame();

        while(paramsFrame.getParam().getNumberOfPoints() == null ||
                paramsFrame.getParam().getFocusingAccuracy() == null)
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch(IllegalMonitorStateException e)
            {
                //do nothing
            }
        }

        lensFrame.setVisible(true);
        return paramsFrame.getParam();
    }

    public void setListLens(List<Lens> listLens) {
        lensFrame.listLens = listLens;
    }

    public void drawView() {
        lensFrame.repaintLens();
    }

}
