package model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.Before;
import model.MemoryAccess;
import model.Memory;
import java.util.Map;
import java.util.HashMap;


public class MemoryAccessTestJU {

  private static MemoryAccess testMEM;
  private static Memory memoryBanks;
  private static Map<String, Integer> cycles = new HashMap<String, Integer>(5);
  private static Integer memAdd;
     
  @BeforeClass
  public static void SetCycles()
 	{
	 cycles.put("lw", 2);
	 cycles.put("sw", 3);
	 cycles.put("lb", 1);
	 cycles.put("sb", 1);   
	 
	 memoryBanks = new Memory(100);
     memAdd = new Integer(55);
     memoryBanks.setValueAt(4,memAdd);
 	}
  
  @Before
 	public void ResetState() {
	  testMEM = new MemoryAccess( memoryBanks, 1, cycles);
 	}  
  
  
//addToPreMEM(String opName, int seq, int rs, int rt, int imm, int cycles)
  
  @Test
  public void lwTest() {
      testMEM.addToPreMEM("lw", 100, 0, 5, 4, 2);
      testMEM.processClockCycle();
      testMEM.processClockCycle();
      assertEquals(testMEM.getCyclesProcessed(), 2);
      assertEquals(testMEM.getPostMEMSequenceNum(true), 100);
      assertEquals(testMEM.getPostMEMDestReg(), 5);
      assertEquals(testMEM.getPostMEMOpResult(), 55);
  }
  
  @Test
  public void swTest() {
      testMEM.addToPreMEM("sw", 101, 4, 55, 4, 3);
      testMEM.processClockCycle();
      testMEM.processClockCycle();
      testMEM.processClockCycle();
      assertEquals(testMEM.getCyclesProcessed(), 3);
      assertEquals(memoryBanks.getValueAt(8), 55);
  }
  
  @Test
  public void lbTest() {
      testMEM.addToPreMEM("lb", 102, 0, 8, 4, 1);
      testMEM.processClockCycle();
      assertEquals(testMEM.getCyclesProcessed(), 1);
      assertEquals(testMEM.getPostMEMSequenceNum(true), 102);
      assertEquals(testMEM.getPostMEMDestReg(), 8);
      assertEquals(testMEM.getPostMEMOpResult(), 55);
  }
  
  @Test
  public void sbTest() {
      testMEM.addToPreMEM("sb", 103, 4, 55, 4, 1);
      testMEM.processClockCycle();
      assertEquals(testMEM.getCyclesProcessed(), 1);
      assertEquals(memoryBanks.getValueAt(8), 55);
  }
  
 @Test
 public void testMultipleInstructions() {
     testMEM.addToPreMEM("lw", 100, 0, 5, 4, 2);
     assertEquals(testMEM.getCyclesProcessed(), 0);
     testMEM.processClockCycle();
     testMEM.addToPreMEM("sw", 101, 4, 55, 4, 3);
   
     //run the lw op and test results
     testMEM.processClockCycle();
     assertEquals(testMEM.getCyclesProcessed(), 2);
     assertEquals(testMEM.getPostMEMSequenceNum(true), 100);
     assertEquals(testMEM.getPostMEMDestReg(), 5);
     assertEquals((int)testMEM.getPostMEMOpResult(), 55);
     
     //run the sw op and test results
     testMEM.processClockCycle();
     testMEM.processClockCycle();
     testMEM.processClockCycle();
     assertEquals(testMEM.getCyclesProcessed(), 5);
     assertEquals(memoryBanks.getValueAt(8), 55);
 }

 @Test
 public void testBufferFull() {
     //inserting an instruction into a full buffer should fail and return -1
	 testMEM = new MemoryAccess( memoryBanks, 2, cycles);
     assertEquals(testMEM.addToPreMEM("lw", 100, 0, 5, 4, 2), 0);    
     assertEquals(testMEM.addToPreMEM("sw", 101, 4, 55, 4, 3), 0);
     assertEquals(testMEM.addToPreMEM("lb", 100, 8, 6, 4, 1), -1);
 }
 

}   //end class
