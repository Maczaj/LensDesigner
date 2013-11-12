package mma.pszt.view;

import java.util.concurrent.BlockingQueue;

import javax.swing.JFrame;

/**
 * okno programu
 */
class Ramka extends JFrame
{
    private static final long serialVersionUID = -1005384575696676214L;
    /** wy�wiatlany widok na szachownice z figurami */
    private final SzachownicaWidok toCoJest;

    public Ramka()
    {
        setSize(400 + 20, 400 + 40);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Interactive lens designer");
        
        toCoJest = new SzachownicaWidok();
        
        this.add(toCoJest);



    }
    
    /**
     * metoda wy�wietlaj�ca aktualny stan rozgrywki
     */
    void aktualizujStan()
    {
        toCoJest.rysowanieFigur();
        toCoJest.repaint();
    }
    
}
