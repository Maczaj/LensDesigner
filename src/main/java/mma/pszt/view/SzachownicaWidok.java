package mma.pszt.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;

import javax.swing.JPanel;


class SzachownicaWidok extends JPanel implements MouseListener
{
    private static final long serialVersionUID = -7350538643559156062L;

    SzachownicaWidok()
    {
        addMouseListener(this);
    }
    
    /** rysowanie wy�wietlanych komponent�w */
    protected void paintComponent(final Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);

        
    }
    
    /**
     */
    public void rysowanieFigur()
    {
    }
    
    /**
     * obs�uga zarzenia klikni�cia mysz� w oknie szachownicy
     */
    public void mousePressed(final MouseEvent e)
    {


    }
    
    public void mouseReleased(final MouseEvent e)
    {
    }
    
    public void mouseClicked(final MouseEvent e)
    {
    }
    
    public void mouseEntered(final MouseEvent e)
    {
    }
    
    public void mouseExited(final MouseEvent e)
    {
    }
}
