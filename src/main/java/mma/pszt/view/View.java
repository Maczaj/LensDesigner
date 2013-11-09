package mma.pszt.view;/**
 * Created with IntelliJ IDEA.
 * User: maczaj
 * Date: 09.11.13
 * Time: 17:11
 * To change this template use File | Settings | File Templates.
 */

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class View  extends JFrame{
    private static final Logger logger = Logger.getLogger(View.class.getName());

    public View(){
        this.setTitle("Interactive lens designer");

        Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

        //TODO zrobić wyliczanie szerokości i wysokości
        this.setBounds(res.height/4 , res.width/4 ,200 , 200 );
    }

}
