/**
 * Reads in MIPS instructions from a file or from the view and parses them into instructions
 * readable by this simulator.
 * 
 */


package utility;

import interfaces.IInstruction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.BranchInstruction;
import model.ITypeInstruction;
import model.InvalidInstructionException;
import model.JTypeInstruction;
import model.Label;
import model.RTypeInstruction;
import model.Simulation;

public class InstructionParser {
	public Simulation _unnamed_Simulation_;
	List<IInstruction> ilist = new ArrayList<IInstruction>();
	Scanner file;
	private static int lineCounter = 0;
	List<String> invalidlist = new ArrayList<String>();
	List<Label> labellist = new ArrayList<Label>();
	boolean invalidFlag = false;
	Package twolist = new Package();


	/**
	 * Reads from the indicated location a set of MIPS instructions in the MIPS format and outputs
	 * them as a list of instructions that the simulator can read.
	 * @param aInfilePath The location of the instructions.
	 * @return A list of instructions that the simulation can use.
	 */
	public Package LoadInstructions(String aInfilePath){
		try {
			file = new Scanner(new File(aInfilePath));
			file.useDelimiter("[, ()\r\n]+");
		} catch (FileNotFoundException e) {
			twolist.setIlist(ilist);
			return twolist;
		}
		lineCounter = 1;
		invalidFlag = false;
		while(file.hasNext())
		{
		String name = file.next();
/*		String test1 = file.next();
		String test2 = file.next();
		String test3 = file.next();
		String test4 = file.next();
		String test5 = file.next();
*/		
		if(name.charAt(name.length()-1) == ':')
			Label(name);
		
		/**
		 * This will work in JRE7, but errors in JRE6. I need to get that new library linked.
		 */
		switch (name.toLowerCase()) {
		case "jr": JRInstruction();
			break;
		case "bne": BNEInstruction();
			break;
		case "j": JInstruction();
			break;
		case "lw": LWInstruction();
			break;
		case "beq": BEQInstruction();
			break;
		case "addi": ADDIInstruction();
			break;
		case "sw": SWInstruction();
			break;
		case "mul": MULInstruction();
			break;
		case "add": ADDInstruction();
			break;
		case "sub": SUBInstruction();
			break;
		case "sll": SLLInstruction();
			break;
		case "srl": SRLInstruction();
			break;
		case "nop": NOPInstruction();
			break;
		case "and": ANDInstruction();
			break;
		case "or": ORInstruction();
			break;
		case "slt": SLTInstruction();
			break;
		case "slti": SLTIInstruction();
			break;
		case "sltu": SLTUInstruction();
			break;
		case "sltiu": SLTIUInstruction();
			break;
		case "nor": NORInstruction();
			break;
		case "div": DIVInstruction();
			break;
			default: InvalidInstruction(name);
				break;
		}
		lineCounter++;
		}
		
		
		twolist.setIlist(ilist);
		twolist.setInvalidlist(invalidlist);
		return twolist;
	}

	private void Label(String name) {
		// TODO Auto-generated method stub
		//String body = file.nextLine();
		//body = name + body;
		labellist.add(new Label(name, lineCounter));
	}


	private void InvalidInstruction(String name) {
		// TODO Auto-generated method stub
		String body = "Line: " + lineCounter + "\t" + name + file.nextLine();
		invalidlist.add(body);
		invalidFlag = true;
	}

	private void DIVInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 000000;
		int sa = 0;
		String opc = "DIV";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void NORInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 100111;
		int sa = 0;
		String opc = "NOR";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void SLTIUInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int imm = file.nextInt();
		file.nextLine();
		String opc = "SLTIU";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm));

	}

	private void SLTUInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 0;
		int sa = 0;
		String opc = "SLTU";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void SLTIInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int imm = file.nextInt();
		file.nextLine();
		String opc = "SLTI";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm));

	}

	private void SLTInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 0;
		int sa = 0;
		String opc = "SLT";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void ORInstruction() {
		int rd = file.nextInt();
		int rs = file.nextInt();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 100101;
		int sa = 0;
		String opc = "OR";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));
		
	}

	private void ANDInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 100100;
		int sa = 0;
		String opc = "AND";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void NOPInstruction() {
		// TODO Auto-generated method stub
		int rd = 0;
		int rs = 0;
		int rt = 0;
		file.nextLine();
		int funct = 0;
		int sa = 0;
		String opc = "NOP";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void SRLInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int sa = file.nextInt();
		file.nextLine();
		int funct = 000010;
		int rt = 0;
		String opc = "SRL";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void SLLInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int sa = file.nextInt();
		file.nextLine();
		int funct = 000000;
		int rt = 0;
		String opc = "SLL";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void SUBInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 100001;
		int sa = 0;
		String opc = "SUB";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void ADDInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 100000;
		int sa = 0;
		String opc = "ADD";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void MULInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 000000;
		int sa = 0;
		String opc = "MUL";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void SWInstruction() {
		int rs = file.nextInt();
		int imm = file.nextInt();
		int rd = file.nextInt();
		file.nextLine();
		String opc = "SW";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm));
	}

	private void ADDIInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int rs = file.nextInt();
		int imm = file.nextInt();
		file.nextLine();
		String opc = "ADDI";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm));

	}

	private void BEQInstruction() {
		// TODO Auto-generated method stub
		int rs = file.nextInt();
		int rd = file.nextInt();
		String label = file.next();
		file.nextLine();
		int imm = getImmediateFromLable(label);
		String opc = "BEQ";
		ilist.add((IInstruction) new BranchInstruction(opc, rd, rs, imm, label));

	}

	private void LWInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		int imm = file.nextInt();
		int rs = file.nextInt();
		file.nextLine();
		String opc = "LW";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm));
	}

	private void JInstruction() {
		// TODO Auto-generated method stub
		int jdst = file.nextInt();
		file.nextLine();
		ilist.add((IInstruction) new JTypeInstruction(jdst));
	}

	private void JRInstruction() {
		// TODO Auto-generated method stub
		int rs= file.nextInt();
		file.nextLine();
		int rd = 0;
		int rt = 0;
		int funct = 001000;
		int sa = 0;
		String opc = "JR";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void BNEInstruction() {
		// TODO Auto-generated method stub
		int rs = file.nextInt();
		int rd = file.nextInt();
		String label = file.next();
		file.nextLine();
		int imm = getImmediateFromLable(label);
		String opc = "BNE";
		ilist.add((IInstruction) new BranchInstruction(opc, rd, rs, imm, label));
	}

	private int getImmediateFromLable(String label) {
		//int index = 0;
		//for(int i = 0; !(label.equals(labellist.get(i).getName())); i++);
		//return labellist.get(index).getLineNumber() - lineCounter;
		return 0;
	}

}
