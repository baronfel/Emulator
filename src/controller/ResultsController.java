/**
 * 
 */
package controller;

import model.BenchmarkResult;
import model.ProcessorConfiguration;
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

}