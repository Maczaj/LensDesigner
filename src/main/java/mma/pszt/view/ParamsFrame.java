package mma.pszt.view;

/**
 * @author Arkadiusz Szlachetka
 */
import mma.pszt.utils.Parameters;

import javax.swing.*;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParamsFrame {
	JFrame Frame1 = new JFrame("Parameters Frame");
    JButton buttonGo = new JButton ("Go!");
    JPanel jPanel = new JPanel();

	JTextField textFieldOne = new JTextField(10);
    JTextField textFieldTwo = new JTextField(10);
    JTextField textFieldThree = new JTextField(10);
    JTextField textFieldFour = new JTextField(10);

	JPanel jPanelTextFieldOne = new JPanel (new FlowLayout());
    JPanel jPanelTextFieldTwo = new JPanel (new FlowLayout());
    JPanel jPanelTexyFieldThree = new JPanel (new FlowLayout());
    JPanel jPanelTextFieldFour = new JPanel (new FlowLayout());
    JPanel jPanelTextFieldError = new JPanel (new FlowLayout());

	JLabel l1 = new JLabel("Number Of Points:     ");
    JLabel l2 = new JLabel("Number Of Rays :       ");
    JLabel l3 = new JLabel("Focusing Accuracy:  ");
    JLabel l4 = new JLabel("Refractive Index:       ");
    JLabel lError = new JLabel("Wrong Param");

    final Parameters param = new Parameters();

    public ParamsFrame()
	    {
	        jPanelTextFieldOne.add(l1);
    	    jPanelTextFieldOne.add(textFieldOne);
            jPanelTextFieldTwo.add(l2);
            jPanelTextFieldTwo.add(textFieldTwo);
            jPanelTexyFieldThree.add(l3);
            jPanelTexyFieldThree.add(textFieldThree);
            jPanelTextFieldFour.add(l4);
            jPanelTextFieldFour.add(textFieldFour);
            jPanelTextFieldError.add(lError);

            jPanel.add(buttonGo);
            Frame1.add(jPanelTextFieldOne);
            Frame1.add(jPanelTextFieldTwo);
            Frame1.add(jPanelTexyFieldThree);
            Frame1.add(jPanelTextFieldFour);
            Frame1.add(jPanelTextFieldError);
            jPanelTextFieldError.setVisible(false);
            Frame1.add(jPanel);
            Frame1.setLayout(new FlowLayout());
            Frame1.setSize(300,250);
     	    Frame1.setVisible(true);
     	    Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            buttonGo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    {
                        try{
                            param.setNumberOfPoints(new Integer(textFieldOne.getText()));
                            param.setNumberOfRays(new Integer(textFieldTwo.getText()));
                            param.setFocusingAccuracy(new Double(textFieldThree.getText()));
                            param.setRefractiveIndex(new Double(textFieldFour.getText()));

                            Frame1.setVisible(false);

                        }
                        catch(NumberFormatException e1)
                        {
                            jPanelTextFieldError.setVisible(true);
                        }
                    }

                }
            });
     	  }



    public Parameters getParam() {
        return param;
    }


}