package mma.pszt;

import mma.pszt.controller.Controller;
import mma.pszt.model.Lens;

import mma.pszt.utils.Line;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


/**
 * Main class to run the app.
 *
 */
public class LensDesigner
{
	private static final Logger logger = Logger.getLogger(LensDesigner.class.getName());

    public static void main( String[] args )
    {
        PropertyConfigurator.configure("log4j.properties");
    	Controller aa = new Controller();

        logger.debug("Startujemmmmmmyyyyyyyy");
        aa.start();
    }
}
