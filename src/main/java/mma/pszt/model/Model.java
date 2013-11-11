package mma.pszt.model;

import java.util.List;

import mma.pszt.LensDesigner;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Model {

	private static final Logger logger = Logger.getLogger(LensDesigner.class.getName());

	public Model()
	{
        PropertyConfigurator.configure("log");

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
