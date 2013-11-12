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

public class Frame {
	JFrame Frame1 = new JFrame("Parameters Frame");
    JButton buttonGo = new JButton ("Go!");
    JPanel jPanel = new JPanel();

	JTextField textFieldOne = new JTextField(10);
    JTextField textFieldTwo = new JTextField(10);
    JTextField textField3 = new JTextField(10);
    JTextField textField4 = new JTextField(10);

	JPanel j1 = new JPanel (new FlowLayout());
    JPanel j2 = new JPanel (new FlowLayout());
    JPanel j3 = new JPanel (new FlowLayout());
    JPanel j4 = new JPanel (new FlowLayout());

	JLabel l1 = new JLabel("Param 1: ");
    JLabel l2 = new JLabel("Param 2: ");
    JLabel l3 = new JLabel("Param 3: ");
    JLabel l4 = new JLabel("Param 4: ");

    private Parameters param;

    public Frame()
	    {
	        j1.add(l1);
    	    j1.add(textFieldOne);
            j2.add(l2);
            j2.add(textFieldTwo);
            j3.add(l3);
            j3.add(textField3);
            j4.add(l4);
            j4.add(textField4);

            jPanel.add(buttonGo);
            Frame1.add(j1);
            Frame1.add(j2);
            Frame1.add(j3);
            Frame1.add(j4);
            Frame1.add(jPanel);
            Frame1.setLayout(new FlowLayout());
            Frame1.setSize(200,250);
     	    Frame1.setVisible(true);
     	    Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     	  }

    public Parameters getParam() {
        return param;
    }
}