package mma.algorithm.evolution;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Generic implementation of 1+1 evolutionary algorithm.
 * @author maczaj
 *
 */
public class OnePlusOneAlgorithm<T extends Individual> implements EvolutionaryAlgorithm<T> {

	private static final Logger LOGGER = Logger.getLogger(OnePlusOneAlgorithm.class.getName());
	
	//internal attributes
	Random random;
	T individual;
	long iterationNo;
	int fi;
	double sigma;
	double minSigma;
	int lastIterationResult;
		
	//input parameters
	int m;
	double c1;
	double c2;	
	
	
	/**
	 * Creates new OnePlusOneAlgorithm instance.
	 * @param cls - Class object of class implementing Individual interface
	 * @param startingSigma - starting value of sigma
	 * @param minSigma - value of sigma below which simulation is not continued
	 * @param m - number of iterations after that sigma is changed 
	 * @param c1 - multiplicator of sigma when old individual is chosen more often than 1/5 times
	 * @param c2 - multiplicator of sigma when new individual is chosen more often than 1/5 times
	 * <BR><BR>
	 * Suggested values: m = 10 , c1 = 0.82 , c2 = 1.2. Starting value of sigma is problem-specific.
	 * @throws IllegalAccessException - in situation when reflective-based instantiation of cls is not possible (most probably there is no default constructor)
	 * @throws InstantiationException - in situation when reflective-based instantiation of cls is not possible (most probably there is no default constructor)
	 */
	public OnePlusOneAlgorithm(Class<T> cls, double startingSigma, double minSigma, int m, double c1, double c2) throws InstantiationException, IllegalAccessException {
		this.sigma = startingSigma;
		this.minSigma = minSigma;
		this.m = m;
		this.c1 = c1;
		this.c2 = c2;
		
		random = new Random( System.currentTimeMillis() );
		iterationNo = 0;
		fi = 0;
		individual = cls.newInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void nextIteration() {
		++iterationNo;
		LOGGER.info("Iteration No. " + iterationNo);
		double factor = random.nextGaussian();
		
		//TODO: albo inaczej to przemyslec albo @SuppressWarnings (sic!)
		T newIndividual =  (T) individual.mutateIndividual( factor * sigma) ;
		
		if( Individual.comparator.compare(newIndividual, individual) >= 0){
			//new individual is better or equal
			++fi;
			individual = newIndividual;			
		}
		
		//update sigma if it's time...
		if( (this.iterationNo % m ) == 0){
			double times = fi / m;
			
			if( times < 1/5){
				sigma = c1 * sigma;
			}
			else if( times > 1/5){
				sigma = c2 * sigma;
			}
			fi = 0;
		}
		
		//set status of iteration by analysing value of sigma
		if( sigma < minSigma ){
			//minimum sigma reached
			lastIterationResult = -1;
		}
		else {
			//everything OK
			lastIterationResult = 0;
		}		
	}

	/**
	 * {@inheritDoc}
	 * @return 0 when everything was OK <BR>
	 * -1 when minimum sigma was reached
	 */
	@Override
	public int getStatus() {
		return lastIterationResult;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T getObject() {
		return individual;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<T> getGeneration() {
		Set<T> s = new HashSet<>();
		s.add(individual);
		return s;
	}

}
