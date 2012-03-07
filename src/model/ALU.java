package model;

import java.io.*;
import java.lang.Integer;

public class ALU
{
  private int bufferSize;
  private int aluNumber;      //which ALU within the core is it
  private int stallCycles;    //stalls the ALU for the number of cycles specified
  private boolean isPreBuffFull;
  private int currentInstrIndex;    //holds the index in the preALUBuffer of the current instruction
  private PreALUBufferEntry[] preALUBuffer;
  private PostALUBufferEntry postALUBuffer;
  private PreALUBufferEntry currentInstruction;
  
  /**
   * Class constructor that takes the alu number within the core and the pre-ALU buffer size
   * as parameters
  */
  public ALU(int alu, int buffSize)
  {
    aluNumber = alu;
    stallCycles = 0;
    bufferSize = buffSize;
    isPreBuffFull = false; 
    currentInstrIndex = 0;
    preALUBuffer = new PreALUBufferEntry[bufferSize];
    for (int i = 0; i < bufferSize; i++){
      preALUBuffer[i] = new PreALUBufferEntry();
    }
    postALUBuffer = new PostALUBufferEntry();
    currentInstruction =  new PreALUBufferEntry();
  }
  
  /**
   * Class constructor that takes the alu number as a parameter.
   * PreALUBuffer size is set to 1 as a default
  */
  public ALU(int alu)
  {
    aluNumber = alu;
    stallCycles = 0;
    bufferSize = 1;
    isPreBuffFull = false; 
    currentInstrIndex = 0;
    preALUBuffer = new PreALUBufferEntry[bufferSize];
    for (int i = 0; i < bufferSize; i++){
      preALUBuffer[i] = new PreALUBufferEntry();
    }
    postALUBuffer = new PostALUBufferEntry();
    currentInstruction =  new PreALUBufferEntry();
  }
  

  /**
   * Method to process a clock cycle. If stallCycles is greater than 0, this method will just
   * decrement the stallCycle variable then wait for the next clock cycle. If 0, this
   * method will perform the operation of the current instruction and deposit the result
   * in the post ALU buffer
  */
  public void processClockCycle()
  {
    //if there is not an instruction being processed, get the next one from the buffer
    if (currentInstruction.opName == "") {
        currentInstruction = getNextInstruction();
        stallCycles = currentInstruction.numCycles - 1;
      }
    
    if (stallCycles == 0) {
      //go ahead and process the instruction
      String tmpStr = currentInstruction.opName;
      double operationResult = 0;
      if (tmpStr == "Mul") operationResult = mult(currentInstruction.op1Value,currentInstruction.op2Value);
      else if (tmpStr == "Div") operationResult = div(currentInstruction.op1Value,currentInstruction.op2Value);
      else if (tmpStr == "Add") operationResult = add(currentInstruction.op1Value,currentInstruction.op2Value);
      else if (tmpStr == "Sub") operationResult = sub(currentInstruction.op1Value,currentInstruction.op2Value);
      //add more instructions here
      
      //add the result to the post ALU buffer
      addToPostALU(operationResult);
      
      //clear the current instruction
      currentInstruction = new PreALUBufferEntry();
      
    }
    else {
      //just decrement the stallCycles and wait for the next clock cycle
      stallCycles--;
    }
  }  //end processClockCycle
  
  
  /**
   * Method to perform an addition operation 
   */
  private double add(double op1, double op2)
  {
    return (op1 + op2);
  }
  
  
  /**
   * Method to perform a subtraction operation 
   */
  private double sub(double op1, double op2)
  {
    return (op1 - op2);
  }
  
  /**
   * Method to perform a division operation 
   */
  private double div(double op1, double op2)
  {
    if (op2 == 0) {
      //return an error code
      
      //add code
      return -1;
    }
    else {
      return (op1 / op2);
    }
  }
  
  /**
   * Method to perform a multiplication operation 
   */
  private double mult(double op1, double op2)
  {
    return (op1 * op2);
  }
  
  
  /**
   * Method to add an instruction to the pre-ALU buffer. Returns 0 if the 
   * instruction was successfully added, otherwise returns -1 if buffer is full
   */
  public int addToPreALU(String opName, int seq, int op1, int op2, int dest, int cycles)
  {
    for (int i = 0; i < preALUBuffer.length; i++) {  
      if (preALUBuffer[i].opName == "") {       //then add the new instruction here
        preALUBuffer[i].opName = opName;
        preALUBuffer[i].progSequenceNumber = seq;
        preALUBuffer[i].op1Value = op1;
        preALUBuffer[i].op2Value = op2;
        preALUBuffer[i].destinationRegister = dest;
        preALUBuffer[i].numCycles = cycles;
        return 0;
      }
    }
    return -1;
  }
  
  
  /**
   * Method to get the next instruction from the pre-ALU buffer
   */
  private PreALUBufferEntry getNextInstruction()
  {
    //take the first entry from the buffer
    PreALUBufferEntry tmpInstruction = new PreALUBufferEntry(preALUBuffer[0]);
    
    //move the remaining buffer entries forward
    for (int i = 0; i < preALUBuffer.length - 1; i++) {
        preALUBuffer[i] = preALUBuffer[i + 1];
    }
    preALUBuffer[preALUBuffer.length - 1] = new PreALUBufferEntry();
        
    return tmpInstruction;
  }
  
  
  /**
   * Method to add a completed instruction to the post-ALU buffer. Return 0 if successful,
   * return -1 if the buffer is full
   */
  private int addToPostALU(double result)
  {
    if (postALUBuffer.progSequenceNumber != 0) {
      postALUBuffer.progSequenceNumber = currentInstruction.progSequenceNumber;
      postALUBuffer.destinationRegister = currentInstruction.destinationRegister;
      postALUBuffer.opResult = result;
      return 0;
    }
    else {
      return -1;
    }
  }
  
  
  /**
   * Method to get the program sequence number from the post-ALU buffer. Returns the program
   * sequence number of the most recently completed instruction or if the buffer
   * is empty the method returns -1. The clear parameter set to true will empty the 
   * buffer, false will leave the value in the buffer
   * Will be used by the Writeback unit
   */
  public int getPostALUSequenceNum(boolean clear)
  {
    int tmpSeqNum = postALUBuffer.progSequenceNumber;
    
    if (tmpSeqNum >= 0 && clear == true) {
      postALUBuffer.progSequenceNumber = -1;
    }
   
    return tmpSeqNum;
  }
  
  
  /**
   * Method to get the destination register number from the post-ALU buffer. 
   * Will be used by the Writeback unit
   */
  public int getPostALUDestReg()
  {
    return postALUBuffer.destinationRegister;
  }
  
  
  /**
   * Method to get the operation result from the post-ALU buffer. 
   * Will be used by the Writeback unit
   */
  public double getPostALUOpResult()
  {
    return postALUBuffer.opResult;
  }
  
   /**
   * Method to get the ALU number. Used only for class testing. 
   */
  public int getALUNumber()
  {
    return this.aluNumber;
  }
  
  /**
   * Method to get the buffer size. Used only for class testing. 
   */
  public int getBufferSize()
  {
    return this.bufferSize;
  }
  
  /**
   * Methods to return contents of the pre ALU buffer. Used only for class testing.
   */
  public String getPreALUOpName(int index){return this.preALUBuffer[index].opName; } 
  public int getPreALUProgSeqNum(int index){return this.preALUBuffer[index].progSequenceNumber; }  
  public double getPreALUOp1Val(int index){return this.preALUBuffer[index].op1Value; }  
  public double getPreALUOp2Val(int index){return this.preALUBuffer[index].op2Value; }
  public int getPreALUDestReg(int index){return this.preALUBuffer[index].destinationRegister; }
  public int getPreALUNumCycles(int index){return this.preALUBuffer[index].numCycles; }
  public String getCurrentInstrOpName(){return this.currentInstruction.opName; }
  
   /**
   * Class to store pre-ALU instructions
   */
  private class PreALUBufferEntry
  {
    private String opName;
    private int progSequenceNumber;
    private double op1Value;   //first operand value (rs)
    private double op2Value;   //second operand value (either rt or immediate)
    private int destinationRegister;   //destination register
    private int numCycles;           //the number of clock cycles this instruction takes
    
    /**
    * Class constructor
    */
    private PreALUBufferEntry()
    {
      opName = "";
      progSequenceNumber = -1;
      op1Value = 0;
      op2Value = 0;
      destinationRegister = 0;
      numCycles = 0;
    }
    
    /**
    * Class copy constructor
    */
    private PreALUBufferEntry(PreALUBufferEntry entry)
    {
      opName = entry.opName;
      progSequenceNumber = entry.progSequenceNumber;
      op1Value = entry.op1Value;
      op2Value = entry.op2Value;
      destinationRegister = entry.destinationRegister;
      numCycles = entry.numCycles;
    }
  }
  
  /**
  * Class to store post-ALU instructions
  */
  private class PostALUBufferEntry
  {
    private int progSequenceNumber;
    private int destinationRegister;   //register number to store the result
    private double opResult;
    
    private PostALUBufferEntry()
    {
      progSequenceNumber = -1;
      destinationRegister = 0;
      opResult = 0;
    }
  }
   
}  //end of class ALU