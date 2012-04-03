package model.test;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import interfaces.IALU;
import interfaces.IMemoryAccess;
import interfaces.IWriteBack;
import model.ALU;
import model.Memory;
import model.MemoryAccess;
import model.Registry;
import model.WriteBack;

public class WriteBackTest {

  private Map<String, Integer> opCycles;
  private List<IALU> alus = new ArrayList<IALU>(2);
  private Memory memoryBanks = new Memory(100);
  private IMemoryAccess memory = new MemoryAccess(102, 4, 444);
  private Registry registers = new Registry();
  
  @Test
  public void ALUListProcessesCorrectly()
  {
   alus.add(new ALU(100, 2, 222));
   alus.add(new ALU(101, 3, 333));
   WriteBack writeBack = new WriteBack(memory, alus, registers); 
   writeBack.processClockCycle(); 
   assertEquals(registers.getValue(2), 222); 
   assertEquals(registers.getValue(3), 333);  
  }

  @Test
  public void MemoryAccessProcessesCorrectly()
  {
   WriteBack writeBack = new WriteBack(memory, alus, registers); 
   writeBack.processClockCycle(); 
   assertEquals(registers.getValue(4), 444); 
  }
}  //end class









