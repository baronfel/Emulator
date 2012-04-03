package model.test;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

import model.MemoryAccess;
import model.Memory;

class MemoryAccessTest{
  private static MemoryAccess testMEM;
  private static Memory memoryBanks;
  
   public static void main(String[] args){
 
     int aluNumber = 1;
     int buffSize = 4;
     //int aluNumber = Integer.parseInt(args[0]);
     //int buffSize = Integer.parseInt(args[1]);
       
     //create an MEM object
     Map<String, Integer> cycles = new HashMap<String, Integer>(50);
     cycles.put("lw", 4);
     cycles.put("sw", 2);
     
     memoryBanks = new Memory(1000);
     Integer memAdd = new Integer(55);
     memoryBanks.setValueAt(100,memAdd);
     
     testMEM = new MemoryAccess( memoryBanks, buffSize, cycles);
     System.out.println(testMEM);
     System.out.println("Pre MEM buffer size: " + testMEM.getBufferSize());
     System.out.println("----------------------------");
     
     //call clock cycles
     System.out.println("Clock cycle : 0");
     System.out.println("----------------------------");
     int added = 0;
     boolean singleCycle = true;
     
     for (int j = 1; j < 23; j++) {
       //enter instructions while the buffer is not full
                                     //addToPreMEM(String opName, int seq, int rs, int rt, int imm, int cycles)
       if (testMEM.getPreMEMOpName( buffSize - 1) == "" && added < 1) {                  
         System.out.println("Insert prog sequence 100 result: " + testMEM.addToPreMEM("lw", 100, 5, 0, 100, 4));
         added++; } 
       else if (testMEM.getPreMEMOpName(buffSize - 1) == "" && added < 2) {
         System.out.println("Insert prog sequence 101 result: " + testMEM.addToPreMEM("sw", 101, 55, 4, 100, 2));
         added++; }
       else if (testMEM.getPreMEMOpName(buffSize - 1) == "" && added < 3) {
         System.out.println("Insert prog sequence 102 result: " + testMEM.addToPreMEM("lw", 102, 8, 4, 100, 4));
         added++; }
       else if (testMEM.getPreMEMOpName(buffSize - 1) == "" && added < 4) {
         System.out.println("Insert prog sequence 103 result: " + testMEM.addToPreMEM("sw", 103, 55, 8, 100, 2));
         added++; }
       else if (testMEM.getPreMEMOpName(buffSize - 1) == "" && added < 5) {
         System.out.println("Insert prog sequence 104 result: " + testMEM.addToPreMEM("lw", 104, 12, 8, 100, 4));
         added++; }
       else if (testMEM.getPreMEMOpName(buffSize - 1) == "" && added < 6) {
         System.out.println("Insert prog sequence 105 result: " + testMEM.addToPreMEM("sw", 105, 55, 12, 100, 2));
         added++; }
       else if (testMEM.getPreMEMOpName(buffSize - 1) != "") {
         System.out.println("Pre MEM buffer full - no insertion");
       }
       
       //output the preMEM buffer each cycle
       System.out.println("Pre MEM Buffer State:");
       System.out.println();
       outputPreMEMBuffer(buffSize);
       System.out.println("----------------------------");
       
       //process a clock cycle
       String tmpOp = testMEM.getCurrentInstrOpName();
       testMEM.processClockCycle();
       
       //output the results of the clock cycle
       if (testMEM.getPostMEMSequenceNum(false) == -1 && tmpOp != "sw") {
         System.out.println("Clock cycle : " + j + " - stall cycle");
         System.out.println("----------------------------");
       }
       else 
         if (tmpOp == "lw") {    //output the operation results from the post MEM register
           System.out.println("----------------------------");
           System.out.println("Clock cycle : " + j + " - processed prog seq " + testMEM.getPostMEMSequenceNum(true));
           System.out.println("Operation: " + tmpOp);
           System.out.println("Destination register : " + testMEM.getPostMEMDestReg());
           System.out.println("Value to load : " + testMEM.getPostMEMOpResult());
           System.out.println("----------------------------");
         }
         else {     //it was a store word, output the contents of the memory
         
           System.out.println("----------------------------");
           System.out.println("Clock cycle : " + j + " - processed prog seq " + testMEM.getPostMEMSequenceNum(true));
           System.out.println("Operation: " + tmpOp);
           System.out.println("Value at memory addr. 100 : " + memoryBanks.getValueAt(100));
           System.out.println("Value at memory addr. 104 : " + memoryBanks.getValueAt(104));
           System.out.println("Value at memory addr. 108 : " + memoryBanks.getValueAt(108));
           System.out.println("Value at memory addr. 112 : " + memoryBanks.getValueAt(112));
           System.out.println("----------------------------");
         }
         
         if (singleCycle) {
           System.out.print("Press Enter to process next cycle, c to complete the program");
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           String input = null;
           try { input = br.readLine(); }
           catch (IOException e) { }
           if (input.matches("c")) {
             singleCycle = false;
           }
         }
         
       
     }    //end for
     
   }   //end main
   
  
   private static void outputPreMEMBuffer(int buff)
   {
     for (int i = 0; i < buff; i++) {
       System.out.println("Pre MEM Buffer " + i + ":");
       System.out.println("OpName: " + testMEM.getPreMEMOpName(i));  
       System.out.println("Program Sequence: " + testMEM.getPreMEMProgSeqNum(i)); 
       System.out.println("RS Value: " + testMEM.getPreMEMrsVal(i)); 
       System.out.println("RT Value: " + testMEM.getPreMEMrtVal(i)); 
       System.out.println("Immediate Value: " + testMEM.getPreMEMimmVal(i)); 
       System.out.println("Cycles: " + testMEM.getPreMEMNumCycles(i)); 
       System.out.println();
     }
   }
  
}