/**
 * Labels are places in code that you can branch to. This class should only be used in an
 * instruction parser.
 * @see InstructionParser
 */
package model;


public class Label {
	String label;
	int lineNumber;

	public Label(){
		super();
	}
	public Label(String name, int ln){
		label = name;
		lineNumber = ln;
	}
	
	public String getName(){
		return label;
	}
	
	public int getLineNumber(){
		return lineNumber;
	}
}
