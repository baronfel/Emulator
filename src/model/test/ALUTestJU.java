package model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import model.ALU;

//import interfaces.IALU;

//import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ALUTestJU {
  
  private static ALU testALU;
  private static Map<String, Integer> cycles = new HashMap<String, Integer>(5);
  
 @BeforeClass
 public static void SetCycles()
	{
	 cycles.put("mul", 3);
	 cycles.put("add", 1);
	 cycles.put("div", 4);
	 cycles.put("sub", 2);
	 cycles.put("addi", 1);
	 cycles.put("addiu", 1);
	 cycles.put("la", 1);
	 cycles.put("li", 1);
	 cycles.put("sll", 1);
	 cycles.put("srl", 1);
	 cycles.put("and", 1);
	 cycles.put("or", 1);
	 cycles.put("nor", 1);
	 cycles.put("slt", 1);
	 cycles.put("slti", 1);
	 cycles.put("sltu", 1);
	 cycles.put("sltiu", 1);
	}
 
 @Before
	public void ResetState() {
	 testALU = new ALU(1, 4, cycles);
	}
 
//Note: addToPreALU(String opName, int seq, int op1, int op2, int dest)
 
 @Test
 public void multTest() {
	 testALU.addToPreALU("mul", 100, 20, 15, 4);
	 testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 3);
     assertEquals(testALU.getPostALUSequenceNum(true), 100);
     assertEquals(testALU.getPostALUDestReg(true), 4);
     assertEquals(testALU.getPostALUOpResult(), 300);
 }
 
 @Test
 public void addTest() {
	 testALU.addToPreALU("add", 101, 56, 14, 5);
	 testALU.processClockCycle();
	 assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 101);
     assertEquals(testALU.getPostALUDestReg(true), 5);
     assertEquals(testALU.getPostALUOpResult(), 70);
 }
 
 
 @Test
 public void divTest() {
	 testALU.addToPreALU("div", 102, 1000, 40, 6);
	 testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 4);
     assertEquals(testALU.getPostALUSequenceNum(true), 102);
     assertEquals(testALU.getPostALUDestReg(true), 33);
     assertEquals(testALU.getPostALUDestReg2(true), 32);
     assertEquals(testALU.getPostALUOpResult(), 25);
     assertEquals(testALU.getPostALUOpResult2(), 0);
 }

 @Test
 public void divFractionTest() {
	 testALU.addToPreALU("div", 103, 46, 12, 6);
	 testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 4);
     assertEquals(testALU.getPostALUSequenceNum(true), 103);
     assertEquals(testALU.getPostALUDestReg(true), 33);
     assertEquals(testALU.getPostALUDestReg2(true), 32);
     assertEquals(testALU.getPostALUOpResult(), 3);
     assertEquals(testALU.getPostALUOpResult2(), 10);
 } 
 
 @Test
 public void divByZeroTest() {
	 testALU.addToPreALU("div", 104, 8, 0, 6);
	 testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 4);
     assertEquals(testALU.getPostALUSequenceNum(true), 104);
     assertEquals(testALU.getPostALUDestReg(true), 33);
     assertEquals(testALU.getPostALUDestReg2(true), 32);
     assertEquals(testALU.getPostALUOpResult(), 0);
     assertEquals(testALU.getPostALUOpResult2(), 0);
 } 
 
 @Test
 public void subTest() {
	 testALU.addToPreALU("sub", 105, 20, 15, 7);
	 testALU.processClockCycle();
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 2);
     assertEquals(testALU.getPostALUSequenceNum(true), 105);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 5);
 }
 
 @Test
 public void addiTest() {
	 testALU.addToPreALU("addi", 106, 22, -11, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 106);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 11);
 }
 
 @Test
 public void addiuTest() {
	 testALU.addToPreALU("addiu", 107, 22, 11, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 107);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 33);
 }
 
 @Test
 public void laTest() {
	 testALU.addToPreALU("la", 108, 0, 3456, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 108);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 3456);
 }
 
 @Test
 public void liTest() {
	 testALU.addToPreALU("li", 109, 0, 6543, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 109);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 6543);
 }
 
 @Test
 public void sllTest() {
	 testALU.addToPreALU("sll", 110, 111, 3, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 110);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 888);
 }
 
 @Test
 public void srlTest() {
	 testALU.addToPreALU("srl", 111, 888, 3, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 111);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 111);
 }
 
 @Test
 public void andTest() {
	 testALU.addToPreALU("and", 112, 174, 147, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 112);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 130);
 }
 
 @Test
 public void orTest() {
	 testALU.addToPreALU("or", 113, 174, 147, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 113);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 191);
 }
 
 @Test
 public void norTest() {
	 testALU.addToPreALU("nor", 114, 174, 147, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 114);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), -192);
 }
 
 @Test
 public void sltTrueTest() {
	 testALU.addToPreALU("slt", 115, 5, 8, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 115);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 1);
 }
 
 @Test
 public void sltFalseTest() {
	 testALU.addToPreALU("slt", 116, 8, 5, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 116);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 0);
 }
 
 @Test
 public void sltiTrueTest() {
	 testALU.addToPreALU("slti", 117, -87, -45, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 117);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 1);
 }
 
 @Test
 public void sltiFalseTest() {
	 testALU.addToPreALU("slti", 118, 45, 5, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 118);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 0);
 }
 
 @Test
 public void sltuTrueTest() {
	 testALU.addToPreALU("sltu", 119, -19, 4, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 119);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 1);
 }
 
 @Test
 public void sltuFalseTest() {
	 testALU.addToPreALU("sltu", 120, 243, 0, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 120);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 0);
 }
 
 @Test
 public void sltiuTrueTest() {
	 testALU.addToPreALU("sltu", 121, 19, 48, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 121);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 1);
 }
 
 @Test
 public void sltiuFalseTest() {
	 testALU.addToPreALU("sltu", 122, 23, -567, 7);
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 1);
     assertEquals(testALU.getPostALUSequenceNum(true), 122);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 0);
 }
 
 
 
 @Test
 public void testMultipleInstructions() {
 	
     testALU.addToPreALU("mul", 100, 20, 15, 4);
     testALU.addToPreALU("add", 101, 56, 14, 5);
     testALU.addToPreALU("div", 102, 1000, 40, 6);
     testALU.addToPreALU("sub", 103, 20, 15, 7);
       
     assertEquals(testALU.getCyclesProcessed(), 0);   
     
     //run the mult op and test results
     testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 3);
     assertEquals(testALU.getPostALUSequenceNum(true), 100);
     assertEquals(testALU.getPostALUDestReg(true), 4);
     assertEquals(testALU.getPostALUOpResult(), 300);
     
     //run the add op and test results
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 4);
     assertEquals(testALU.getPostALUSequenceNum(true), 101);
     assertEquals(testALU.getPostALUDestReg(true), 5);
     assertEquals(testALU.getPostALUOpResult(), 70);
     
     //run the div op and test results
     testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 8);
     assertEquals(testALU.getPostALUSequenceNum(true), 102);
     assertEquals(testALU.getPostALUDestReg(true), 33);
     assertEquals(testALU.getPostALUDestReg2(true), 32);
     assertEquals(testALU.getPostALUOpResult(), 25);
     assertEquals(testALU.getPostALUOpResult2(), 0);
     
     //run the sub op and test results
     testALU.processClockCycle();
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 10);
     assertEquals(testALU.getPostALUSequenceNum(true), 103);
     assertEquals(testALU.getPostALUDestReg(true), 7);
     assertEquals(testALU.getPostALUOpResult(), 5);
 
 }
 
 @Test
 public void testBufferFull() {
     //inserting an instruction into a full buffer should fail and return -1
     assertEquals(testALU.addToPreALU("mul", 100, 20, 15, 4), 0);    
     assertEquals(testALU.addToPreALU("add", 101, 56, 14, 5), 0);
     assertEquals(testALU.addToPreALU("sub", 103, 20, 15, 7), 0);
     assertEquals(testALU.addToPreALU("add", 101, 56, 14, 5), 0);
     assertEquals(testALU.addToPreALU("div", 102, 1000, 40, 6), -1);
 }
 
 

}   //end class
