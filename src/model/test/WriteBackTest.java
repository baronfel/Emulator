package model.test;

import static org.junit.Assert.assertEquals;
import interfaces.IALU;
import interfaces.IMemoryAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.ALU;
import model.Registry;
import model.WriteBack;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class WriteBackTest {

	private Map<String, Integer> opCycles;
	private List<IALU> alus = new ArrayList<IALU>(2);
	private final IMemoryAccess memory = Mockito.mock(IMemoryAccess.class);
	private Registry registers = new Registry();

	@Before
	public void ResetState() {
		alus = new ArrayList<IALU>(2);
	}

	@Test
	public void ALUListProcessesCorrectly() {

		Mockito.when(memory.getPostMEMSequenceNum(false)).thenReturn(-1);
		IALU fake1 = Mockito.mock(IALU.class);
		Mockito.when(fake1.getPostALUDestReg()).thenReturn(2);
		Mockito.when(fake1.getPostALUOpResult()).thenReturn(222);
		Mockito.when(fake1.getPostALUSequenceNum(Mockito.anyBoolean())).thenReturn(100);
		IALU fake2 = Mockito.mock(IALU.class);
		Mockito.when(fake2.getPostALUDestReg()).thenReturn(3);
		Mockito.when(fake2.getPostALUOpResult()).thenReturn(333);
		Mockito.when(fake2.getPostALUSequenceNum(Mockito.anyBoolean())).thenReturn(101);
		alus.add(fake1);
		alus.add(fake2);
		
		WriteBack writeBack = new WriteBack(memory, alus, registers);
		writeBack.processClockCycle();
		
		assertEquals(registers.getValue(2), 222);
		assertEquals(registers.getValue(3), 333);
	}

	@Test
	public void MemoryAccessProcessesCorrectly() {
		
		IALU fakeALU = Mockito.mock(IALU.class);
		Mockito.when(fakeALU.getPostALUSequenceNum(false)).thenReturn(-1);
		Mockito.when(memory.getPostMEMSequenceNum(true)).thenReturn(0);
		Mockito.when(memory.getPostMEMDestReg()).thenReturn(4);
		Mockito.when(memory.getPostMEMOpResult()).thenReturn(444);
		alus.add(fakeALU);

		WriteBack writeBack = new WriteBack(memory, alus, registers);
		writeBack.processClockCycle();
		
		assertEquals(registers.getValue(4), 444);
	}
} // end class

