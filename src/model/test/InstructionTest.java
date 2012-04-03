/**
 * 
 */
package model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.ITypeInstruction;
import model.JTypeInstruction;
import model.RTypeInstruction;


/**
 * @author Bob
 *
 */
public class InstructionTest {
	
	private ITypeInstruction addi = new ITypeInstruction();
	private ITypeInstruction subi = new ITypeInstruction("subi", 4, 5, 324, 2);
	private RTypeInstruction add = new RTypeInstruction("add", 2, 3, 4, 5, 6, 3);
	private RTypeInstruction sub = new RTypeInstruction();
	private JTypeInstruction jmp1 = new JTypeInstruction(50000, 5);
	private JTypeInstruction jmp2 = new JTypeInstruction();
	
	@Test
	public void IConstructorsWork()
	{
		addi.setImmediate(505);
		addi.setOpcode("addi");
		addi.setRD(3);
		addi.setRS(6);
		
		assertTrue(addi.toString().equals("addi 3, 6, 505\n"));
		assertTrue(subi.toString().equals("subi 4, 5, 324\n"));
	}
	
	@Test
	public void RConstructorsWork()
	{
		sub.setFUNCT(12);
		sub.setOpcode("sub");
		sub.setRD(8);
		sub.setRS(9);
		sub.setRT(10);
		sub.setSHAMT(11);
		
		assertTrue(add.toString().equals("add 2, 3, 4\n"));
		assertTrue(sub.toString().equals("sub 8, 9, 10\n"));
	}
	
	@Test
	public void JConstructorsWork()
	{
		jmp2.setJumpdest(25000);
		
		assertTrue(jmp1.toString().equals("J 50000\n"));
		assertTrue(jmp2.toString().equals("J 25000\n"));
	}

}
