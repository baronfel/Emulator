/**
 * This is the interface for all Processors in the MIPS Simulator program.
 * 
 */

package model;

import interfaces.ICore;
import java.util.List;
import java.util.ArrayList;

public interface IProcessor {
	public List<ICore> _cores = new ArrayList<ICore>();
}