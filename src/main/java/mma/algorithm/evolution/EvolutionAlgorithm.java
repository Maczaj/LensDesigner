package mma.algorithm.evolution;

import java.util.Set;

/**
 * Interface for evolution algorithms.
 * @author maczaj
 *
 */
public interface EvolutionAlgorithm <T extends Individual> {

	/**
	 * Processes next iteration of evolutionary algorithm.
	 */
	public void nextIteration();
	
	/**
	 * Returns status of last iteration.
	 * @return 
	 */
	public int getStatus();
	
	/**
	 * Return best individual from current generation.
	 * @return the best individual
	 */
	public T getObject();
	
	/**
	 * Return set contatining whole generation of individuals.
	 * @return set containing generation
	 */
	public Set<T> getGeneration();
}
