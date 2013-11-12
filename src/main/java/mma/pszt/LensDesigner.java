package mma.pszt;

import mma.pszt.controller.Controller;
import org.apache.log4j.Logger;


/**
 * Main class to run the app.
 *
 */
public class LensDesigner
{
	private static final Logger logger = Logger.getLogger(LensDesigner.class.getName());

    public static void main( String[] args )
    {
    	Controller aa = new Controller();

        logger.debug("Startujemmmmmmyyyyyyyy");
        aa.start();
    }
}
