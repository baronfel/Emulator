package model;
 
public class ALU
{
  private int bufferSize;
  private int aluNumber;      //which ALU within the core is it
  private int stallCycles;    //stalls the ALU for the number of cycles specified
  private boolean isPreBuffFull;
  private int currentInstrIndex;    //holds the index in the preALUBuffer of the current instruction
  private PreALUBufferEntry[] preALUBuffer;
  private PostALUBufferEntry[] postALUBuffer;
  private PreALUBufferEntry currentInstruction;
  
  public ALU(int alu, int buffSize)
  {
    aluNumber = alu;
    stallCycles = 0;
    bufferSize = buffSize;
    isPreBuffFull = false; 
    currentInstrIndex = 0;
    preALUBuffer = new PreALUBufferEntry[bufferSize];
    postALUBuffer = new PostALUBufferEntry[bufferSize];
  }

  public ALU(int alu)
  {
    aluNumber = alu;
    stallCycles = 0;
    bufferSize = 10;
    isPreBuffFull = false; 
    currentInstrIndex = 0;
    preALUBuffer = new PreALUBufferEntry[bufferSize];
    postALUBuffer = new PostALUBufferEntry[bufferSize];
  }
  

  /**
   * Method to process a clock cycle
  */
  public void processClockCycle()
  {
    if (stallCycles == 0) {
    //go ahead and process the next instruction
    
      
      
      
      
      //add code
      
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
  public int addToPreALU(String opName, int seq, int op1, int op2, int dest)
  {
    for (int i = 0; i < bufferSize; i++) {  
      if (preALUBuffer[i].opName != "") {       //then add the new instruction here
        preALUBuffer[i].opName = opName;
        preALUBuffer[i].progSequenceNumber = seq;
        preALUBuffer[i].op1Value = op1;
        preALUBuffer[i].op2Value = op2;
        preALUBuffer[i].destinationRegister = dest;
        return 0;
      }
    }
    return -1;
  }
  
  
  /**
   * Method to remove an instruction from the pre-ALU buffer
   */
  private void removeFromPreALU()
  {
    
  }
  
  
  /**
   * Method to add an instruction to the post-ALU buffer
   */
  private void addToPostALU()
  {
    
  }
  
  
  /**
   * Method to remove an instruction from the post-ALU buffer
   */
  private void removeFromPostALU()
  {
    
  }
  
  /**
   * Method to retrieve a processed instruction from the post ALU buffer
   */
  public void getNextInstruction()
  {
    
    
    
  }
  
  
  /**
   * Method to advance the entries in a buffer
   */
  private void advanceBufferEntries()
  {
    
  
    
  }
  
  
   
   /**
   * Class to store pre-ALU instructions
   */
  private class PreALUBufferEntry
  {
    private String opName;
    private int progLineNumber;   //possibly not needed, depends on how tracking instructions
    private int progSequenceNumber;
    private double op1Value;   //first operand value (rs)
    private double op2Value;   //second operand value (either rt or immediate)
    private int destinationRegister;   //destination register
    
    private PreALUBufferEntry()
    {
      opName = "";
      progLineNumber = 0;
      progSequenceNumber = 0;
      op1Value = 0;
      op2Value = 0;
      destinationRegister = 0;
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
      progSequenceNumber = 0;
      destinationRegister = 0;
      opResult = 0;
    }
  }
   
}  //end of class ALU