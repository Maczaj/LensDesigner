package mma.pszt;

import mma.pszt.model.Lens;
import mma.pszt.model.LensSegment;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.List;

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

    	Lens lens = new Lens();

        Lens anotherLens = new Lens(lens , 0.15);

        logger.debug(lens);
        logger.debug(anotherLens);

        StringBuilder sb = new StringBuilder("Segments ");

        List<LensSegment> l = lens.getLeftSegments();

        for(LensSegment seg : l){
            sb.append(seg.toString() + "|| ");
            logger.debug(seg.getAsLineEquation());
        }
        logger.debug("Left segs of standard lens: " + sb.toString());
    }
}
