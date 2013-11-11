package mma.pszt.view;/**
 * Created with IntelliJ IDEA.
 * User: maczaj
 * Date: 09.11.13
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */

import lombok.Setter;
import lombok.Synchronized;
import mma.pszt.model.Lens;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class View  extends JFrame{
    private static final Logger logger = Logger.getLogger(View.class.getName());

    private SimulationPanel simulationPanel;

    public View(){
        this.setTitle("Interactive lens designer");

        Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

        //TODO zrobić wyliczanie szerokości i wysokości
        this.setBounds(res.height/4 , res.width/4 ,800 , 600 );
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
    }

    public void setLenses(List<Lens> lenses){
        simulationPanel.setLenses(lenses);
    }
}
