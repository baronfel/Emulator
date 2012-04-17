/**
 * This is the interface for all Processors in the MIPS Simulator program.
 * 
 */

package interfaces;

import java.util.List;
import java.util.ArrayList;

import model.Registry;

public interface IProcessor extends IModel{
	public List<ICore> _cores = new ArrayList<ICore>();

	Registry getRegistry();
	
	public void Cycle();
	
	public ProcStatus getStatus();

	IIssueUnit getIssue();

	List<IALU> getALUs();

	List<IMemoryAccess> getMemUnits();

	IFetchUnit getFetchUnit();
}