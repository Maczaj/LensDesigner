package mma.algorithm.evolution;

import java.util.Set;

/**
 * Interface for evolution algorithms.
 * @author maczaj
 *
 */
public interface EvolutionaryAlgorithm <T extends Individual> {

	/**
	 * Processes next iteration of evolutionary algorithm.<BR>
	 * It does not check fitness-based stop condition - it's up to user of class. However, it checks if minimum sigma has been reached.
	 */
	public void nextIteration();
	
	/**
	 * Returns status of last iteration.
	 * @return 0 if everything is OK, other values are algorithm-specific
	 */
	public int getStatus();
	
	/**
	 * Return best individual from current generation.
	 * @return the best individual
	 */
	public T getObject();
	
	/**
	 * Return set containing whole generation of individuals.
	 * @return set containing generation
	 */
	public Set<T> getGeneration();
}
