/**
 * The Model for our MIPS Simulator. It will simulate a MIPS architecture with varying amounts of
 * components and accept MIPS instructions in order to simulate those instructions using those
 * components. It will also create benchmarks for performance of the simulations.
 * 
 */


package model;

import interfaces.IInstruction;

import java.util.List;

import utility.InstructionParser;

public class Simulation extends AbstractModel {
	private ProcessorConfiguration _processorConfiguration;
	private IProcessor _processor;
	private List<IInstruction> _instructionList;
	public IInstruction _unnamed_IInstruction_;
	public IProcessor _unnamed_IProcessor_;
	public InstructionParser _unnamed_InstructionParser_;
	public ProcessorConfiguration _unnamed_ProcessorConfiguration_;
	public BenchmarkResult _unnamed_BenchmarkResult_;
	public Memory internalMemory;
	
	public Simulation(ProcessorConfiguration config, List<IInstruction> program)
	{
		_instructionList = program;
		_processorConfiguration = config;
		_processor = CreateProcessor(_processorConfiguration);
	}
/**
 * Gets the benchmark results of the previous simulation.
 * @return The Benchmark Results of the previous Simulation.
 */
	public BenchmarkResult GetBenchmarkResult() {
		throw new UnsupportedOperationException();
	}
/**
 * Creates the processors from the given processor configuration.
 * @param aIn_Config The processor configuration.
 * @return The created processors.
 */
	private IProcessor CreateProcessor(ProcessorConfiguration config) {
		throw new UnsupportedOperationException();
	}
}