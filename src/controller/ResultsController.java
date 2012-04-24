/**
 * 
 */
package controller;

import model.BenchmarkResult;
import model.ProcessorConfiguration;
import model.Simulation;
import utility.Package;

/**
 * @author Chet
 *
 */
public class ResultsController {

	private BenchmarkResult _result;
	private ProcessorConfiguration _config;
	
	public ResultsController(BenchmarkResult result) {
		_result = result;
		_config = (ProcessorConfiguration)result.simulation.getModel();
	}

	public ResultsController() {
		_result = new BenchmarkResult();
	}

	public ProcessorConfiguration getProcessorConfig() {
		return _config;
	}

	public Package getProgram() {
		return _result.simulation.getPackage();
	}
	
	public int getCyclesToFinish()
	{
		return _result._cyclesToComplete;
	}
	
	public int getNoops()
	{
		return _result._noops;
	}
	
	public int getMemoryValueAt(int position)
	{
		return _result.simulation.getMemory().getValueAt(position);
	}

	public Simulation getSimulation() {
		return _result.simulation;
	}

}