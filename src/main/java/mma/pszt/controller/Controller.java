package mma.pszt.controller;

import mma.pszt.model.EvaluatedLens;
import mma.pszt.model.Model;
import mma.pszt.utils.Parameters;
import mma.pszt.view.View;
import org.apache.log4j.Logger;

public class Controller {

    //view
    private final View view;
    private final Model model;

    private static final Logger logger = Logger.getLogger(Controller.class.getName());


    public Controller() {
        this.view = new View();
        this.model = new Model(new Parameters(100, 50, 3.0, 3.0, 0.2));
    }

    public void start() {
        final Parameters params = view.setParameters();

//        while (!params.checkIfParamsCorrect()) {
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

//        System.out.println(params.getFocusingAccuracy());
//        System.out.println(params.getNumberOfPoints());
//        System.out.println(params.getNumberOfRays());
//        System.out.println(params.getRefractiveIndex());

        EvaluatedLens lens = model.getLens();
        view.createFrame(lens);

//        model.setParameters(params);

        while (true) {
            try {
                while (model.getLens() == null)
                    Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            int generationResult = model.nextIteration();

            if( generationResult < 0 ){
                logger.info("Simulation finished without result!");
                System.exit(0);
            }
            else if( generationResult > 0 ){
                logger.info("Simulation finished with result in generation no. " + generationResult + " with score " + model.getLens().getScore());
                try{
                    Thread.sleep(20000);
                }
                catch(Exception e){
                    //be silent like a ninja...
                }

                System.exit(0);
            }

            lens = model.getLens();
            view.setLens(lens);
            view.drawView();
        }
    }

}
