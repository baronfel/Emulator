/**
 * 
 */
package model;


/**
 * @author Chester
 *
 */
public class ComponentStatus {

	private String name, status, instruction;
	
	public ComponentStatus(String name, String status, String instr) {
		this.name = name;
		this.status = status;
		this.instruction = instr;
	}

	public String getName() {
		return name;
	}

	public String getStatus() {
		return status;
	}

	public String getCurrentInstruction() {
		return instruction;
	}

}
