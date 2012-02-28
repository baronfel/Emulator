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
	private ITypeInstruction subi = new ITypeInstruction(7, 4, 5, 324);
	private RTypeInstruction add = new RTypeInstruction(1, 2, 3, 4, 5, 6);
	private RTypeInstruction sub = new RTypeInstruction();
	private JTypeInstruction jmp1 = new JTypeInstruction(50000);
	private JTypeInstruction jmp2 = new JTypeInstruction();
	
	@Test
	public void IConstructorsWork()
	{
		addi.setImmediate(505);
		addi.setOpcode(4);
		addi.setRD(3);
		addi.setRS(6);
		
		assertTrue(addi.toString().equals("4 3, 6, 505\n"));
		assertTrue(subi.toString().equals("7 4, 5, 324\n"));
	}
	
	@Test
	public void RConstructorsWork()
	{
		sub.setFUNCT(12);
		sub.setOpcode(7);
		sub.setRD(8);
		sub.setRS(9);
		sub.setRT(10);
		sub.setSHAMT(11);
		
		assertTrue(add.toString().equals("1, 2, 3, 4, 5, 6\n"));
		assertTrue(sub.toString().equals("7, 8, 9, 10, 11, 12\n"));
	}
	
	@Test
	public void JConstructorsWork()
	{
		jmp2.setJumpdest(25000);
		
		assertTrue(jmp1.toString().equals("jump 50000\n"));
		assertTrue(jmp2.toString().equals("jump 25000\n"));
	}

}
