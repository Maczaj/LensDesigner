package mma.pszt.controller;

import java.util.LinkedList;

import mma.pszt.model.EvaluatedLens;
import mma.pszt.utils.Parameters;
import mma.pszt.view.View;
import mma.pszt.model.Model;

import org.apache.log4j.Logger;

public class Controller {
	
	//view
	private final View view;
	private final Model model;

    private static final Logger logger = Logger.getLogger(Controller.class.getName());
    
    
    public Controller()
    {
    	this.view = new View();
    	this.model = new Model();
    }

    public void start()
    {
        final Parameters params = view.setParameters();

        while(!params.checkIfParamsCorrect())
        {
            try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
        System.out.println(params.getFocusingAccuracy());
        System.out.println(params.getNumberOfPoints());
        System.out.println(params.getNumberOfRays());
        System.out.println(params.getRefractiveIndex());

        EvaluatedLens lens = model.getLens();
        view.createFrame(lens);

    //    model.setParameters(params);

        while(true)
        {
            try {
                while(model.getLens() == null)
                    Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                 Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
             lens = model.getLens();

                view.setLens(lens);
                view.drawView();
        }
    }

}
