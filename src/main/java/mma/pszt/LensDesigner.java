package mma.pszt;

import org.apache.log4j.BasicConfigurator;
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
    	//na razie lipny log4j
    	BasicConfigurator.configure(); 
    	
    	logger.info("Hello world!!!");
    }
}
