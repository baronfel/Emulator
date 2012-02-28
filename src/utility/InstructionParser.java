/**
 * Reads in MIPS instructions from a file or from the view and parses them into instructions
 * readable by this simulator.
 * 
 */


package utility;

import interfaces.IInstruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.ITypeInstruction;
import model.JTypeInstruction;
import model.RTypeInstruction;
import model.Simulation;

public class InstructionParser {
	public Simulation _unnamed_Simulation_;
	List<IInstruction> ilist = new ArrayList<IInstruction>();
	Scanner file = new Scanner("aInfilePath");



	/**
	 * Reads from the indicated location a set of MIPS instructions in the MIPS format and outputs
	 * them as a list of instructions that the simulator can read.
	 * @param aInfilePath The location of the instructions.
	 * @return A list of instructions that the simulation can use.
	 */
	public List<IInstruction> LoadInstructions(String aInfilePath) {
		while(file.hasNext())
		{
		String name = file.next();
		
		/**
		 * This will work in JRE7, but errors in JRE6. I need to get that new library linked.
		 */
		switch (name.toLowerCase()) {
		case "JR": JRInstruction();
			break;
		case "BNE": BNEInstruction();
			break;
		case "J": JInstruction();
			break;
		case "LW": LWInstruction();
			break;
		case "BEQ": BEQInstruction();
			break;
		case "ADDI": ADDIInstruction();
			break;
		case "SW": SWInstruction();
			break;
		case "MUL": MULInstruction();
			break;
		case "ADD": ADDInstruction();
			break;
		case "SUB": SUBInstruction();
			break;
		case "SLL": SLLInstruction();
			break;
		case "SRL": SRLInstruction();
			break;
		case "NOP": NOPInstruction();
			break;
		case "AND": ANDInstruction();
			break;
		case "OR": ORInstruction();
			break;
		case "SLT": SLTInstruction();
			break;
		case "SLTI": SLTIInstruction();
			break;
		case "SLTU": SLTUInstruction();
			break;
		case "SLTIU": SLTIUInstruction();
			break;
		case "NOR": NORInstruction();
			break;
		case "DIV": DIVInstruction();
			default: break;
		}
		
		}
		
		return ilist;
	}

	private void DIVInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		file.next();
		int rs = file.nextInt();
		file.next();
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
		file.next();
		int rs = file.nextInt();
		file.next();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 100111;
		int sa = 0;
		String opc = "NOR";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void SLTIUInstruction() {
		// TODO Auto-generated method stub
		
	}

	private void SLTUInstruction() {
		// TODO Auto-generated method stub
		
	}

	private void SLTIInstruction() {
		// TODO Auto-generated method stub
		
	}

	private void SLTInstruction() {
		// TODO Auto-generated method stub
		
	}

	private void ORInstruction() {
		int rd = file.nextInt();
		file.next();
		int rs = file.nextInt();
		file.next();
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
		file.next();
		int rs = file.nextInt();
		file.next();
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
		file.next();
		int rs = file.nextInt();
		file.next();
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
		file.next();
		int rs = file.nextInt();
		file.next();
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
		file.next();
		int rs = file.nextInt();
		file.next();
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
		file.next();
		int rs = file.nextInt();
		file.next();
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
		file.next();
		int rs = file.nextInt();
		file.next();
		int rt = file.nextInt();
		file.nextLine();
		int funct = 000000;
		int sa = 0;
		String opc = "MUL";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa, funct));

	}

	private void SWInstruction() {
		int rd = file.nextInt();
		file.next();
		int imm = file.nextInt();
		file.next("(");
		int rs = file.nextInt();
		file.nextLine();
		String opc = "SW";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm));
	}

	private void ADDIInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		file.next();
		int rs = file.nextInt();
		file.next();
		int imm = file.nextInt();
		file.nextLine();
		String opc = "ADDI";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm));

	}

	private void BEQInstruction() {
		// TODO Auto-generated method stub
		int rs = file.nextInt();
		file.next();
		int rd = file.nextInt();
		file.next();
		String label = file.next();
		file.nextLine();
		int imm = getImmediateFromLable(label);
		String opc = "BEQ";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm));

	}

	private void LWInstruction() {
		// TODO Auto-generated method stub
		int rd = file.nextInt();
		file.next();
		int imm = file.nextInt();
		file.next("(");
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
		file.next();
		int rd = file.nextInt();
		file.next();
		String label = file.next();
		file.nextLine();
		int imm = getImmediateFromLable(label);
		String opc = "BNE";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm));
	}

	private int getImmediateFromLable(String label) {
		// TODO Auto-generated method stub
		return 0;
	}

}