package mma.pszt.controller;/**
 * Created with IntelliJ IDEA.
 * User: maczaj
 * Date: 09.11.13
 * Time: 17:07
 * To change this template use File | Settings | File Templates.
 */

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import mma.pszt.view.View;
import mma.pszt.model.Model;
import mma.pszt.Event;

import org.apache.log4j.Logger;

public class Controller {
	
	//view
	private final View viewer;
	private final Model model;
	private final BlockingQueue<Event> blockingQueue;
	
	
    private static final Logger logger = Logger.getLogger(Controller.class.getName());
    
    
    public Controller()
    {
    	this.viewer = new View();
    	this.model = new Model();
    	this.blockingQueue = new LinkedBlockingQueue<Event>();;
    }

}
