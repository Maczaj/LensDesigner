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
}
