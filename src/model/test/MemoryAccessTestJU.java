package model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import interfaces.IMemoryAccess;
import model.MemoryAccess;
import model.Memory;
import java.util.Map;
import java.util.HashMap;


public class MemoryAccessTestJU {

  private static MemoryAccess testMEM;
  private static Memory memoryBanks;
  Map<String, Integer> cycles = new HashMap<String, Integer>(5);
  
     
  //Note: addToPreALU(String opName, int seq, int op1, int op2, int dest)
  
 @Test
 public void testMultipleInstructions() {
     cycles.put("lw", 2);
     cycles.put("sw", 3);
     
     memoryBanks = new Memory(100);
     Integer memAdd = new Integer(55);
     memoryBanks.setValueAt(4,memAdd);
     
     testMEM = new MemoryAccess( memoryBanks, 1, cycles);
     
     //addToPreMEM(String opName, int seq, int rs, int rt, int imm, int cycles)
     testMEM.addToPreMEM("lw", 100, 5, 0, 4, 2);
     testMEM.addToPreMEM("sw", 101, 55, 4, 4, 3);
       
     assertEquals(testMEM.getCyclesProcessed(), 0);   
     
     //run the lw op and test results
     testMEM.processClockCycle();
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
     //assertEquals(memoryBanks.getValueAt(8), 55);
 
 }

}   //end class
