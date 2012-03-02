package utility;

import interfaces.IInstruction;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 */

/**
 * @author Bob
 *
 */
public class Package {
	private List<IInstruction> ilist = new ArrayList<IInstruction>();
	private List<String> invalidlist = new ArrayList<String>();
	public List<IInstruction> getIlist() {
		return ilist;
	}
	public void setIlist(List<IInstruction> ilist) {
		this.ilist = ilist;
	}
	public List<String> getInvalidlist() {
		return invalidlist;
	}
	public void setInvalidlist(List<String> invalidlist) {
		this.invalidlist = invalidlist;
	}

}
