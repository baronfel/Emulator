/**
 * This is the interface for all Processors in the MIPS Simulator program.
 * 
 */

package interfaces;

import java.util.List;
import java.util.ArrayList;

import model.Memory;
import model.Registry;

public interface IProcessor extends IModel{
	public List<ICore> _cores = new ArrayList<ICore>();
/**
 * Returns the registry associated with this processor.
 * @return The registry associated with this processor.
 */
	Registry getRegistry();
	/**
	 * This method processes one clock cycle for each of the processor's components.
	 */
	public void Cycle();
	
	/**
	 * This method returns the status of the Processor, Active if the processer is in use, Inactive if the processor is at rest.
	 * @return The status of the processor.
	 */
	public ProcStatus getStatus();

	/**
	 * This method returns the Issue Unit of the processor.
	 * @return The Issue Unit of the processor.
	 */
	IIssueUnit getIssue();

	/**
	 * This method returns a list of the ALUs for the processor.
	 * @return A list of the ALUs for the processor.
	 */
	List<IALU> getALUs();

	/**
	 * This method returns a list of the Memory Access Units for the processor.
	 * @return A list of the Memory Access Units for the processor.
	 */

	List<IMemoryAccess> getMemUnits();

	/**
	 * This method returns the Fetch Unit for the processor.
	 * @return The fetch unit for the processor.
	 */
	IFetchUnit getFetchUnit();

	/**
	 * This method returns the Memory of the processor.
	 * @return The memory of the processor.
	 */

	Memory getMemory();

	/**
	 * This method returns a specified ALU from the processor.
	 * @param aluToGet The number of the ALU to return;
	 * @return The ALU to return;
	 */
	IALU getALU(int aluToGet);
	/**
	 * This method returns a specified Memory Access Unit from the processor.
	 * @param memToGet The number of the Memory Access Unit to return;
	 * @return The Memory Access Unit to return;
	 */
	IMemoryAccess getMemUnit(int memToGet);
/**
 * This method returns the Write Back Unit of the processor.
 * @return The write back unit of the processor.
 */
	IWriteBack getWriteback();
/**
 * This method returns the number of no-ops the processor has processed.
 * @return The number of no-ops.
 */
	int getNoops();
}