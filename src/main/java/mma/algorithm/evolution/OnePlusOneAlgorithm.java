package mma.algorithm.evolution;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;

import org.apache.log4j.Logger;

/**
 * Generic implementation of 1+1 evolutionary algorithm.
 * @author maczaj
 *
 */
public class OnePlusOneAlgorithm<T extends Individual> implements EvolutionaryAlgorithm<T> {

	private static final Logger LOGGER = Logger.getLogger(OnePlusOneAlgorithm.class.getName());
	
	//internal attributes
	T individual;
	@Getter
	long iterationNo;
	int fi;
	double sigma;
	double minSigma;
	IterationResult lastIterationResult;
		
	//input parameters
	int m;
	double c1;
	double c2;	
	
	
	/**
	 * Creates new OnePlusOneAlgorithm instance. Performs maximization of fitness, multiply your score by -1 if you want minimization.
	 * @param indidividual - referemce to individual treated as starting point
	 * @param startingSigma - starting value of sigma
	 * @param minSigma - value of sigma below which simulation is not continued
	 * @param m - number of iterations after that sigma is changed 
	 * @param c1 - multiplicator of sigma when old individual is chosen more often than 1/5 times
	 * @param c2 - multiplicator of sigma when new individual is chosen more often than 1/5 times
	 * <BR><BR>
	 * Suggested values: m = 10 , c1 = 0.82 , c2 = 1.2. Starting value of sigma is problem-specific.
	 */
	public OnePlusOneAlgorithm(T indidividual, double startingSigma, double minSigma, int m, double c1, double c2) {
		this.sigma = startingSigma;
		this.minSigma = minSigma;
		this.m = m;
		this.c1 = c1;
		this.c2 = c2;
		this.individual = indidividual;
		
		iterationNo = 0;
		fi = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void nextIteration() {
		if ( lastIterationResult == IterationResult.FINISHED_WITHOUT_RESULT) {
			//minimum sigma reached, there's no point in continuation 
			return;
		}
		
		++iterationNo;
		LOGGER.info("Iteration No. " + iterationNo);
		
		//TODO: albo inaczej to przemyslec albo @SuppressWarnings (sic!)
		T newIndividual =  (T) individual.mutateIndividual( sigma) ;
		
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
			lastIterationResult = IterationResult.FINISHED_WITHOUT_RESULT;
		}
		else {
			//everything OK
			lastIterationResult = IterationResult.OK;
		}		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IterationResult getStatus() {
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
