package mma.pszt.view;/**
 * Created with IntelliJ IDEA.
 * User: maczaj
 * Date: 09.11.13
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */
/*
import lombok.Setter;
import lombok.Synchronized;
import mma.pszt.model.Lens;
import mma.pszt.utils.Parameters;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class View  extends JFrame{
    private static final Logger logger = Logger.getLogger(View.class.getName());

    private SimulationPanel simulationPanel;
    private List<Lens> listLens;

    public View(){

        this.setTitle("Interactive lens designer");

        Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

        //TODO zrobić wyliczanie szerokości i wysokości
        this.setBounds(res.height/4 , res.width/4 ,800 , 600 );
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        this.add(new SimulationPanel());
    }

    public void setLenses(List<Lens> lenses){
        simulationPanel.setLenses(lenses);
    }

    public Parameters setParameters() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setListLens(List<Lens> listLens) {
        this.listLens = listLens;
    }

    public void drawView() {
        //To change body of created methods use File | Settings | File Templates.
    }

}
*/

import mma.pszt.model.Lens;
import mma.pszt.utils.Parameters;

import java.util.List;

import javax.swing.SwingUtilities;

/**
 *
 */
public class View
{
    /**Okno programu.*/
    private final Ramka ramka;

    /**
     * Konstruktor inicjalizujący.
     *
     */
    public View()
    {
        ramka = new Ramka();
        ramka.setVisible(true);
    }

    /**
     * przerysowywanie wikoku na szachownice
     */
    public void jeszczeRaz()
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                ramka.aktualizujStan();
                ramka.repaint();
            }
        });

    }
    public void setLenses(List<Lens> lenses){
      //  simulationPanel.setLenses(lenses);
    }

    public Parameters setParameters() {
        Frame params = new Frame();
    //    ParametersSet setParam = new ParametersSet();
    //    setParam.setVisible(true);

        return params.getParam();  //To change body of created methods use File | Settings | File Templates.
    }

    public void setListLens(List<Lens> listLens) {
     //   this.listLens = listLens;
    }

    public void drawView() {
        //To change body of created methods use File | Settings | File Templates.
    }

}
