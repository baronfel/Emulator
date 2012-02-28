/**
 * Handles and stores all benchmarking data for simulations.
 * @see Simulation
 */


package model;

public class BenchmarkResult {
	/**
	 * The number of cycles it took to complete the program.
	 */
	public int _cyclesToComplete = 0;
	
	/**
	 * The number of errors that were encountered in the running of the program.
	 */
	public int _errorCount;
	
	/**
	 * The number of stalls that were run into during the program.
	 */
	public int _noops;
	
	/**
	 * The overall simulation that is being run.
	 */
	public Simulation simulation;
}