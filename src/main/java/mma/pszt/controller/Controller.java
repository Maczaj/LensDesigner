package mma.pszt.controller;

import mma.algorithm.evolution.EvolutionaryAlgorithm;
import mma.algorithm.evolution.IterationResult;
import mma.algorithm.evolution.OnePlusOneAlgorithm;
import mma.pszt.model.EvaluatedLens;
import mma.pszt.model.Lens;
import mma.pszt.utils.Parameters;
import mma.pszt.view.View;
import mma.pszt.view.Waiter;

import org.apache.log4j.Logger;

public class Controller {
	
	
	private static final Logger logger = Logger.getLogger(Controller.class.getName());

    private final View view;
//    private Model model;
// TODO przerobić kontroler pod nowe interfejsy
    private EvolutionaryAlgorithm<EvaluatedLens> algorithm;

    public Controller() {
        this.view = new View();    
    }

    public void start() {
        Parameters params = view.setParameters();

        while (!params.checkIfParamsCorrect()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        EvaluatedLens lens = new EvaluatedLens(new Lens(params.getNumberOfPoints()), params);
        this.algorithm = new OnePlusOneAlgorithm<EvaluatedLens>(lens, 1.0, 0.3, 10, 0.82, 1.2);
        
        view.createFrame(lens);
        boolean sth = true;
        

        while (true) {
            Waiter.getInstance().stop();
            try {
              if( sth ){
                    Thread.sleep(1000);
                    sth = false;
              }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //process
            algorithm.nextIteration();
            IterationResult generationResult = algorithm.getStatus();

            lens = algorithm.getObject();
            view.setLens(lens);
            view.drawView();

            if (generationResult == IterationResult.FINISHED_WITHOUT_RESULT ) {
                logger.info("Simulation finished without result!");
                System.exit(0);                
            }
            // TODO: w jakis sposób zaimplementować wybór, czy fitness ma byc minimalizowany czy maksymalizowany
            int score = lens.getScore();
            logger.info("Best fitness is now " + generationResult);
            if (score > params.getFocusingAccuracy() ) {
                logger.info("Simulation finished with result in generation no. " + algorithm.getIterationNo() + " with score " + generationResult);
                try {
                    Thread.sleep(20000);
                } catch (Exception e) {
                    System.err.println(e);
                }

                System.exit(0);
            }
        }
    }
}
