package utility;

import interfaces.IInstruction;

import java.util.ArrayList;
import java.util.List;

import model.Label;
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
	private List<String> parserCommands = new ArrayList<String>();
	private List<Label> labellist = new ArrayList<Label>();
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
	public List<Label> getLabellist() {
		return labellist;
	}
	public void setLabellist(List<Label> labellist) {
		this.labellist = labellist;
	}
	public List<String> getParserCommands() {
		return parserCommands;
	}
	public void setParserCommands(List<String> parserCommands) {
		this.parserCommands = parserCommands;
	}

}
