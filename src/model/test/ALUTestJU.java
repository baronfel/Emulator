package model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import interfaces.IALU;
import model.ALU;
import java.util.Map;
import java.util.HashMap;

public class ALUTestJU {
  
  private static ALU testALU;
  Map<String, Integer> cycles = new HashMap<String, Integer>(5);
  
     
  //Note: addToPreALU(String opName, int seq, int op1, int op2, int dest)
  
 @Test
 public void testMultipleInstructions() {
     cycles.put("mul", 3);
     cycles.put("add", 1);
     cycles.put("div", 4);
     cycles.put("sub", 2);
     testALU = new ALU(1, 4, cycles);
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
     assertEquals(testALU.getPostALUDestReg(), 4);
     assertEquals((int)testALU.getPostALUOpResult(), 300);
     
     //run the add op and test results
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 4);
     assertEquals(testALU.getPostALUSequenceNum(true), 101);
     assertEquals(testALU.getPostALUDestReg(), 5);
     assertEquals((int)testALU.getPostALUOpResult(), 70);
     
     //run the div op and test results
     testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 8);
     assertEquals(testALU.getPostALUSequenceNum(true), 102);
     assertEquals(testALU.getPostALUDestReg(), 6);
     assertEquals((int)testALU.getPostALUOpResult(), 25);
     
     //run the sub op and test results
     testALU.processClockCycle();
     testALU.processClockCycle();
     assertEquals(testALU.getCyclesProcessed(), 10);
     assertEquals(testALU.getPostALUSequenceNum(true), 103);
     assertEquals(testALU.getPostALUDestReg(), 7);
     assertEquals((int)testALU.getPostALUOpResult(), 5);
 
 }

}   //end class
