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
import model.JTypeInstruction;
import model.Label;
import model.RTypeInstruction;

public class InstructionParser {
	static List<IInstruction> ilist = new ArrayList<IInstruction>();
	static Scanner file;
	private static int lineCounter = 0;
	static List<String> invalidlist = new ArrayList<String>();
	static List<Label> labellist = new ArrayList<Label>();
	static List<String> parserCommands = new ArrayList<String>();
	static boolean invalidFlag = false; // Used to determine if an invalid
										// instruction exception should be
										// thrown NOT CURRENTLY IN USE
	static Package listPackage = new Package();

	/**
	 * Reads from the indicated location a set of MIPS instructions in the MIPS
	 * format and outputs them as a list of instructions that the simulator can
	 * read.
	 * 
	 * @param aInfilePath
	 *            The location of the instructions.
	 * @return A list of instructions that the simulation can use.
	 */
	public static Package LoadInstructions(String aInfilePath) {
		listPackage = new Package();
		ilist = new ArrayList<IInstruction>();
		invalidlist = new ArrayList<String>();
		labellist = new ArrayList<Label>();
		parserCommands = new ArrayList<String>();

		LoadLabelsAndCommands(aInfilePath);
		try {
			file = new Scanner(new File(aInfilePath));
			file.useDelimiter("[, ()\\r\\n\\t]+");
		} catch (FileNotFoundException e) {
			listPackage.setIlist(ilist);
			return listPackage;
		}
		lineCounter = 0;
		invalidFlag = false;
		while (file.hasNext()) {
			String name = file.next();
			if (name.equals(""))
				;
			else if (name.charAt(name.length() - 1) == ':')
				;
			// Label(name);
			else {
				/**
				 * This will work in JRE7, but errors in JRE6. I need to get
				 * that new library linked.
				 */
				switch (name.toLowerCase()) {
				case "jr":
					JRInstruction();
					break;
				case "bne":
					BNEInstruction();
					break;
				case "j":
					JInstruction();
					break;
				case "lw":
					LWInstruction();
					break;
				case "beq":
					BEQInstruction();
					break;
				case "beqz":
					BEQZInstruction();
					break;
				case "addi":
					ADDIInstruction();
					break;
				case "addiu":
					ADDIUInstruction();
					break;
				case "sw":
					SWInstruction();
					break;
				case "lb":
					LBInstruction();
					break;
				case "la":
					LAInstruction();
					break;
				case "li":
					LIInstruction();
					break;
				case "sb":
					SBInstruction();
					break;
				case "mul":
					MULInstruction();
					break;
				case "add":
					ADDInstruction();
					break;
				case "sub":
					SUBInstruction();
					break;
				case "sll":
					SLLInstruction();
					break;
				case "srl":
					SRLInstruction();
					break;
				case "nop":
					NOPInstruction();
					break;
				case "and":
					ANDInstruction();
					break;
				case "andi":
					ANDIInstruction();
					break;
				case "or":
					ORInstruction();
					break;
				case "ori":
					ORIInstruction();
					break;
				case "slt":
					SLTInstruction();
					break;
				case "slti":
					SLTIInstruction();
					break;
				case "sltu":
					SLTUInstruction();
					break;
				case "sltiu":
					SLTIUInstruction();
					break;
				case "nor":
					NORInstruction();
					break;
				case "div":
					DIVInstruction();
					break;
				case "move":
					MOVInstruction();
					break;
				case "bge":
					BGEInstruction();
					break;
				case "bnez":
					BNEZInstruction();
					break;
				default:
					InvalidInstruction(name);
					lineCounter--;
					break;
				}
				lineCounter++;
			}
		}
		listPackage.setIlist(ilist);
		listPackage.setInvalidlist(invalidlist);
		return listPackage;
		/**
		 * The code to use invalid flag to determine how to throw an invalid
		 * instruction exception would go just above here.
		 */
	}

	private static void BNEZInstruction() {
		// TODO Auto-generated method stub
		int rs = getValue(file.next());
		String label = file.next();
		file.nextLine();
		int imm = getImmediateFromLabel(label);
		String opc = "BNEZ";
		ilist.add((IInstruction) new BranchInstruction(opc, 0, rs, imm,
				lineCounter, label));	
	}

	private static void BGEInstruction() {
		// TODO Auto-generated method stub
		int rs = getValue(file.next());
		int rd = getValue(file.next());
		String label = file.next();
		file.nextLine();
		int imm = getImmediateFromLabel(label);
		String opc = "BGE";
		ilist.add((IInstruction) new BranchInstruction(opc, rd, rs, imm,
				lineCounter, label));
	}

	private static void MOVInstruction() {
		// TODO Auto-generated method stub
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int imm = 0;
		file.nextLine();
		String opc = "MOVE";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));		
	}

	private static void ORIInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int imm = file.nextInt();
		file.nextLine();
		String opc = "ORI";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));
	}

	private static void LIInstruction() {
		int rd = getValue(file.next());
		int imm = file.nextInt();
		file.nextLine();
		String opc = "LI";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, 0, imm,
				lineCounter));
	}

	private static void LAInstruction() {
		int rd = getValue(file.next());
		String label = file.next();
		file.nextLine();
		int imm = getImmediateFromLabel(label);
		String opc = "LA";
		ilist.add((IInstruction) new BranchInstruction(opc, rd, 0, imm,
				lineCounter, label));
	}

	private static void SBInstruction() {
		int rs = getValue(file.next());
		int imm = file.nextInt();
		int rd = getValue(file.next());
		file.nextLine();
		String opc = "SB";
		// if(imm > byte)
		// invalid instruction
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));
	}

	private static void LBInstruction() {
		int rd = getValue(file.next());
		int imm = file.nextInt();
		int rs = getValue(file.next());
		file.nextLine();
		String opc = "LB";
		// if(imm > byte)
		// invalid instruction
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));
	}

	private static void ANDIInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int imm = file.nextInt();
		file.nextLine();
		String opc = "ANDI";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));
	}

	private static void ADDIUInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int imm = file.nextInt();
		file.nextLine();
		String opc = "ADDIU";
		// if(imm < 0)
		// invalid instruction
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));
	}

	private static void BEQZInstruction() {
		int rs = getValue(file.next());
		String label = file.next();
		file.nextLine();
		int imm = getImmediateFromLabel(label);
		String opc = "BEQZ";
		ilist.add((IInstruction) new BranchInstruction(opc, 0, rs, imm,
				lineCounter, label));
	}

	private static void LoadLabelsAndCommands(String aInfilePath) {
		try {
			file = new Scanner(new File(aInfilePath));
			file.useDelimiter("[, ()\\r\\n\\t\t]+");
		} catch (FileNotFoundException e) {
			listPackage.setIlist(ilist);
			return;
		}
		lineCounter = 0;
		//while (file.hasNextLine()) {
		while (file.hasNext()) {
			String name = file.next();
			String body = name + file.nextLine();
			if (body.equals(""))
				;
			else if (body.charAt(0) == '.')
				Command(body);
			else if (body.charAt(body.length() - 1) == ':')
				Label(body);
			if(validInstruction(name))
				lineCounter++;
		}
		file = null;
		listPackage.setLabellist(labellist);
		listPackage.setParserCommands(parserCommands);
	}

	private static boolean validInstruction(String name) {
		switch (name.toLowerCase()) {
		case "jr":
			break;
		case "bne":
			break;
		case "j":
			break;
		case "lw":
			break;
		case "beq":
			break;
		case "beqz":
			break;
		case "addi":
			break;
		case "addiu":
			break;
		case "sw":
			break;
		case "lb":
			break;
		case "la":
			break;
		case "li":
			break;
		case "sb":
			break;
		case "mul":
			break;
		case "add":
			break;
		case "sub":
			break;
		case "sll":
			break;
		case "srl":
			break;
		case "nop":
			break;
		case "and":
			break;
		case "andi":
			break;
		case "or":
			break;
		case "ori":
			break;
		case "slt":
			break;
		case "slti":
			break;
		case "sltu":
			break;
		case "sltiu":
			break;
		case "nor":
			break;
		case "div":
			break;
		default:
			return false;
		}
		return true;
	}

	private static void Command(String name) {
		String body = "Line: " + lineCounter + "\t" + name + file.nextLine();
		parserCommands.add(body);
	}

	private static void Label(String name) {
		labellist.add(new Label(name.substring(0, name.length() - 1),
				lineCounter));
	}

	private static void InvalidInstruction(String name) {
		String body = "Line: " + lineCounter + "\t" + name + file.nextLine();
		invalidlist.add(body);
		invalidFlag = true;
	}

	private static void DIVInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int rt = getValue(file.next());
		file.nextLine();
		int funct = 000000;
		int sa = 0;
		String opc = "DIV";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void NORInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int rt = getValue(file.next());
		file.nextLine();
		int funct = 100111;
		int sa = 0;
		String opc = "NOR";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void SLTIUInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int imm = file.nextInt();
		file.nextLine();
		String opc = "SLTIU";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));

	}

	private static void SLTUInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int rt = getValue(file.next());
		file.nextLine();
		int funct = 0;
		int sa = 0;
		String opc = "SLTU";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void SLTIInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int imm = file.nextInt();
		file.nextLine();
		String opc = "SLTI";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));

	}

	private static void SLTInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int rt = getValue(file.next());
		file.nextLine();
		int funct = 0;
		int sa = 0;
		String opc = "SLT";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void ORInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int rt = getValue(file.next());
		file.nextLine();
		int funct = 100101;
		int sa = 0;
		String opc = "OR";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void ANDInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int rt = getValue(file.next());
		file.nextLine();
		int funct = 100100;
		int sa = 0;
		String opc = "AND";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void NOPInstruction() {
		int rd = 0;
		int rs = 0;
		int rt = 0;
		file.nextLine();
		int funct = 0;
		int sa = 0;
		String opc = "NOP";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void SRLInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int sa = getValue(file.next());
		file.nextLine();
		int funct = 000010;
		int rt = 0;
		String opc = "SRL";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void SLLInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int sa = getValue(file.next());
		file.nextLine();
		int funct = 000000;
		int rt = 0;
		String opc = "SLL";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void SUBInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int rt = getValue(file.next());
		file.nextLine();
		int funct = 100001;
		int sa = 0;
		String opc = "SUB";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void ADDInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int rt = getValue(file.next());
		file.nextLine();
		int funct = 100000;
		int sa = 0;
		String opc = "ADD";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void MULInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int rt = getValue(file.next());
		file.nextLine();
		int funct = 000000;
		int sa = 0;
		String opc = "MUL";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void SWInstruction() {
		int rs = getValue(file.next());
		int imm = file.nextInt();
		int rd = getValue(file.next());
		file.nextLine();
		String opc = "SW";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));
	}

	private static void ADDIInstruction() {
		int rd = getValue(file.next());
		int rs = getValue(file.next());
		int imm = file.nextInt();
		file.nextLine();
		String opc = "ADDI";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));

	}

	private static void BEQInstruction() {
		int rs = getValue(file.next());
		int rd = getValue(file.next());
		String label = file.next();
		file.nextLine();
		int imm = getImmediateFromLabel(label);
		String opc = "BEQ";
		ilist.add((IInstruction) new BranchInstruction(opc, rd, rs, imm,
				lineCounter, label));

	}

	private static void LWInstruction() {
		int rd = getValue(file.next());
		int imm = file.nextInt();
		int rs = getValue(file.next());
		file.nextLine();
		String opc = "LW";
		ilist.add((IInstruction) new ITypeInstruction(opc, rd, rs, imm,
				lineCounter));
	}

	private static void JInstruction() {
		// int jdst = file.next(); //THIS IS FOR REAL J INSTRUCTION, WE ARE
		// USING J INSTRUCTION LIKE PSUEDO B INSTRUCTION
		int jdst = getImmediateFromLabel(file.next());
		// THIS IS FOR B PSEUDO INSTRUCTION, WE ARE USING J INSTRUCTION LIKE IT
		file.nextLine();
		ilist.add((IInstruction) new JTypeInstruction(jdst, lineCounter));
	}

	private static void JRInstruction() {
		int rs = getValue(file.next());
		file.nextLine();
		int rd = 0;
		int rt = 0;
		int funct = 001000;
		int sa = 0;
		String opc = "JR";
		ilist.add((IInstruction) new RTypeInstruction(opc, rd, rs, rt, sa,
				funct, lineCounter));

	}

	private static void BNEInstruction() {
		int rs = getValue(file.next());
		int rd = getValue(file.next());
		String label = file.next();
		file.nextLine();
		int imm = getImmediateFromLabel(label);
		String opc = "BNE";
		ilist.add((IInstruction) new BranchInstruction(opc, rd, rs, imm,
				lineCounter, label));
	}

	private static int getImmediateFromLabel(String label) {
		int index = 0;
		for (int i = 0; i < labellist.size(); i++) {
			if (label.equals(labellist.get(i).getName())) {
				index = i;
			}
		}
		return labellist.get(index).getLineNumber();
	}

	private static int getValue(String regName) {
		for (Register r : Register.values())
			if (regName.toLowerCase().equals(r.getName().toLowerCase()))
				return r.getValue();
		return -1;
	}
}
