package mma.pszt.controller;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import mma.pszt.model.Lens;
import mma.pszt.utils.Parameters;
import mma.pszt.view.View;
import mma.pszt.model.Model;
import mma.pszt.Event;

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

        System.out.println(params.getFocusingAccuracy());
        System.out.println(params.getNumberOfPoints());
        System.out.println(params.getNumberOfRays());
        System.out.println(params.getRefractiveIndex());

        model.setParameters(params);

        while(true)
        {
            List<Lens> listLens = model.getListLens();
            view.setListLens(listLens);
            view.drawView();
        }
    }

}
