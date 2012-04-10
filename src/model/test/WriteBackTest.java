package model.test;


import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

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
  private Mockery mocker = new Mockery();
  private final IMemoryAccess memory = mocker.mock(IMemoryAccess.class);
  private Registry registers = new Registry();
  
  @Before
  public void ResetState()
  {
	  alus = new ArrayList<IALU>(2);
  }
  
  @Test
  public void ALUListProcessesCorrectly()
  {
	  
	  mocker.checking(new Expectations(){{
		oneOf(memory.getPostMEMSequenceNum(false)); returnValue(-1);  
	  }});
	  
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
	  final IALU fakeALU = mocker.mock(IALU.class);
	  final boolean f = false;
	  final boolean t = true;
	  mocker.checking(new Expectations(){{
		  oneOf(memory.getPostMEMSequenceNum(with(any(Boolean.class)))); returnValue(0);
		  oneOf(memory.getPostMEMSequenceNum(with(true))); returnValue(0);
		  oneOf(memory.getPostMEMDestReg()); returnValue(4);
		  oneOf(memory.getPostMEMOpResult()); returnValue(444);
		  oneOf(fakeALU.getPostALUSequenceNum(with(false))); returnValue(-1);
	  }});
	  
	  alus.add(fakeALU);
	  
   WriteBack writeBack = new WriteBack(memory, alus, registers); 
   writeBack.processClockCycle(); 
   assertEquals(registers.getValue(4), 444); 
  }
}  //end class









