package mma.pszt.view;

import javax.swing.JFrame;
import mma.pszt.model.Lens;

import java.util.List;

class LensFrame extends JFrame
{
    private static final long serialVersionUID = -1005384575696676214L;

    private final LensPanel toCoJest;
    public List<Lens> listLens;

    public LensFrame()
    {
        setSize(400 + 20, 400 + 40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Interactive lens designer");
        
        toCoJest = new LensPanel();
        
        this.add(toCoJest);
    }
    

    void repaintLens()
    {

        toCoJest.repaint();
    }
    
}
