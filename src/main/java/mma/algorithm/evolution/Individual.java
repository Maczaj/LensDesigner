package mma.algorithm.evolution;

import java.util.Comparator;

/**
 * Interface to represent single individual.
 * @author maczaj
 *
 */
public interface Individual {
	
	public static final Comparator<Individual> comparator = new Comparator<Individual>() {
		public int compare(Individual o1, Individual o2) {
			return o1.getScore() - o2.getScore();
		}
	};
	
	/**
	 * Evaluates this individuals fitness.
	 * @return fitness of this individual
	 */
	public int getScore();
	
	/**
	 * @param mutationFactor - factor of mutation
	 * @return mutated individual
	 */
	//TODO: zdecydowac czy zostawic tak (po kazdym wywolaniu trzeba bedzie rzutowac wynik na odpowiedni typ), czy moze wprowadzic generyka
	public Individual mutateIndividual( double mutationFactor ); 
	
	/**
	 * (not needed for OnePlusOneMethod)
	 * @param other - second individual used in crossover
	 * @return crossed individual
	 */
	public Individual crossIndividuals ( Individual other );
}
