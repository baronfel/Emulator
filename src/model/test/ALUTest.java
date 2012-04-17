package model.test;

import java.util.HashMap;
import java.util.Map;
import java.io.*;

import model.ALU;

class ALUTest{
  private static ALU testALU;
  
   public static void main(String[] args){
 
     int aluNumber = 1;
     int buffSize = 4;
     //int aluNumber = Integer.parseInt(args[0]);
     //int buffSize = Integer.parseInt(args[1]);
       
     //create an ALU object
     Map<String, Integer> cycles = new HashMap<String, Integer>(50);
     cycles.put("mul", 4);
     cycles.put("add", 1);
     cycles.put("div", 6);
     cycles.put("sub", 2);
     
     testALU = new ALU(aluNumber, buffSize, cycles);
     System.out.println(testALU);
     System.out.println("ALU number: " + testALU.getALUNumber());
     System.out.println("Pre ALU buffer size: " + testALU.getBufferSize());
     System.out.println("----------------------------");
     
     //call clock cycles
     System.out.println("Clock cycle : 0");
     System.out.println("----------------------------");
     int added = 0;
     boolean singleCycle = true;
      
     for (int j = 1; j < 23; j++) {
       //enter instructions while the buffer is not full
       //addToPreALU(String opName, int seq, int op1, int op2, int dest, int cycles)
       if (testALU.getPreALUOpName(buffSize - 1) == "" && added < 1) {
         System.out.println("Insert prog sequence 100 result: " + testALU.addToPreALU("mul", 100, 20, 15, 4));
         added++; }
       else if (testALU.getPreALUOpName(buffSize - 1) == "" && added < 2) {
         System.out.println("Insert prog sequence 101 result: " + testALU.addToPreALU("add", 101, 56, 14, 5));
         added++; }
       else if (testALU.getPreALUOpName(buffSize - 1) == "" && added < 3) {
         System.out.println("Insert prog sequence 102 result: " + testALU.addToPreALU("div", 102, 1000, 40, 7));
         added++; }
       else if (testALU.getPreALUOpName(buffSize - 1) == "" && added < 4) {
         System.out.println("Insert prog sequence 103 result: " + testALU.addToPreALU("add", 103, 20, 15, 4));
         added++; }
       else if (testALU.getPreALUOpName(buffSize - 1) == "" && added < 5) {
         System.out.println("Insert prog sequence 104 result: " + testALU.addToPreALU("div", 104, 12, 28, 5));
         added++; }
       else if (testALU.getPreALUOpName(buffSize - 1) == "" && added < 6) {
         System.out.println("Insert prog sequence 105 result: " + testALU.addToPreALU("sub", 105, 9, 4, 6));
         added++; }
       else if (testALU.getPreALUOpName(buffSize - 1) != "") {
         System.out.println("Pre ALU buffer full - no insertion");
       }
       
       //output the preALU buffer each cycle
       System.out.println("Pre ALU Buffer State:");
       System.out.println();
       outputPreALUBuffer(buffSize);
       System.out.println("----------------------------");
       
       //process a clock cycle
       String tmpOp = testALU.getCurrentInstrOpName();
       testALU.processClockCycle();
       
       //output the results of the clock cycle
       if (testALU.getPostALUSequenceNum(false) == -1) {
         System.out.println("Clock cycle : " + j + " - stall cycle");
         System.out.println("----------------------------");
       }
       else {    //output the operation results from the post ALU register
         System.out.println("----------------------------");
         System.out.println("Clock cycle : " + j + " - processed prog seq " + testALU.getPostALUSequenceNum(true));
         System.out.println("Operation: " + tmpOp);
         System.out.println("Destination register : " + testALU.getPostALUDestReg(true));
         System.out.println("Operation result : " + testALU.getPostALUOpResult());
         System.out.println("----------------------------");
         
       }
       
       if (singleCycle) {
           System.out.print("Press Enter to process next cycle, c to complete the program");
           BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
           String input = null;
           try { input = br.readLine(); }
           catch (IOException e) { }
           System.out.println("Your name is " + input);
           if (input.matches("c")) {
             singleCycle = false;
           }
         }
     }
   }
   
  
   private static void outputPreALUBuffer(int buff)
   {
     for (int i = 0; i < buff; i++) {
       System.out.println("Pre ALU Buffer " + i + ":");
       System.out.println("OpName: " + testALU.getPreALUOpName(i));  
       System.out.println("Program Sequence: " + testALU.getPreALUProgSeqNum(i)); 
       System.out.println("Operand 1: " + testALU.getPreALUOp1Val(i)); 
       System.out.println("Operand 2: " + testALU.getPreALUOp2Val(i)); 
       System.out.println("Destination Register: " + testALU.getPreALUDestReg(i)); 
       System.out.println("Cycles: " + testALU.getPreALUNumCycles(i)); 
       System.out.println();
     }
   }
  
}