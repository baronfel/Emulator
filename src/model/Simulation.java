/**
 * The Model for our MIPS Simulator. It will simulate a MIPS architecture with varying amounts of
 * components and accept MIPS instructions in order to simulate those instructions using those
 * components. It will also create benchmarks for performance of the simulations.
 * 
 */

package model;

import interfaces.IInstruction;
import interfaces.IModel;
import interfaces.IProcessor;
import interfaces.ProcStatus;

import java.util.List;

import utility.Package;
import controller.AbstractController;

public class Simulation extends AbstractController {
	private ProcessorConfiguration _processorConfiguration;
	private IProcessor _processor;
	public List<IInstruction> _instructionList;
	public IInstruction _unnamed_IInstruction_;
	public IProcessor _unnamed_IProcessor_;
	public ProcessorConfiguration _unnamed_ProcessorConfiguration_;
	public BenchmarkResult _result;
	public Memory internalMemory;
	private Package programInfo;

	public Simulation(ProcessorConfiguration config, Package programInfo) {
		super(config);
		_instructionList = programInfo.getIlist();
		this.programInfo = programInfo;
		_processorConfiguration = config;
		_processor = CreateProcessor(_processorConfiguration);
		_result = new BenchmarkResult();
		_result.simulation = this;
	}

	/**
	 * Gets the benchmark results of the previous simulation.
	 * 
	 * @return The Benchmark Results of the previous Simulation.
	 */
	public BenchmarkResult GetBenchmarkResult() {
		return _result;
	}

	/**
	 * Creates the processors from the given processor configuration.
	 * 
	 * @param aIn_Config
	 *            The processor configuration.
	 * @return The created processors.
	 */
	private IProcessor CreateProcessor(ProcessorConfiguration config) {
		return new Processor(config.GetALUCount(), config.GetCycleMap(),
				_instructionList);
	}

	public Registry getRegistry() {
		return _processor.getRegistry();
	}

	public IProcessor getProcessor() {
		return _processor;
	}

	public Package getPackage() {
		return programInfo;
	}

	@Override
	public void setModel(IModel aModel) {
		if (aModel instanceof ProcessorConfiguration) {
			_processorConfiguration = (ProcessorConfiguration) aModel;
			RestartSimulation();
		}
	}

	private void RestartSimulation() {
		_processor = CreateProcessor(_processorConfiguration);
	}

	public ProcessorConfiguration getProcessorConfig() {
		return _processorConfiguration;
	}
	
	public void Cycle()
	{
		_processor.Cycle();
	}

	public void cycleToEnd() {
		while(_processor.getStatus() == ProcStatus.Active)
		{
			_processor.Cycle();
		}
	}
}