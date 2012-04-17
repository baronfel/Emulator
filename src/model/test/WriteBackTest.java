package model.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import interfaces.IALU;
import interfaces.IMemoryAccess;

import java.util.ArrayList;
import java.util.List;

import model.Registry;
import model.WriteBack;

import org.junit.Before;
import org.junit.Test;

public class WriteBackTest {

	private List<IALU> alus = new ArrayList<IALU>(2);
	private final IMemoryAccess memory = mock(IMemoryAccess.class);
	private Registry registers = new Registry();

	@Before
	public void ResetState() {
		alus = new ArrayList<IALU>(2);
	}

	@Test
	public void ALUListProcessesCorrectly() {

		when(memory.getPostMEMSequenceNum(false)).thenReturn(-1);
		IALU fake1 = mock(IALU.class);
		when(fake1.getPostALUDestReg(true)).thenReturn(2);
		when(fake1.getPostALUOpResult()).thenReturn(222);
		when(fake1.getPostALUSequenceNum(anyBoolean())).thenReturn(100);
		IALU fake2 = mock(IALU.class);
		when(fake2.getPostALUDestReg(true)).thenReturn(3);
		when(fake2.getPostALUOpResult()).thenReturn(333);
		when(fake2.getPostALUSequenceNum(anyBoolean())).thenReturn(101);
		alus.add(fake1);
		alus.add(fake2);
		
		WriteBack writeBack = new WriteBack(memory, alus, registers);
		writeBack.processClockCycle();
		
		assertEquals(registers.getValue(2), 222);
		assertEquals(registers.getValue(3), 333);
	}

	@Test
	public void MemoryAccessProcessesCorrectly() {
		
		IALU fakeALU = mock(IALU.class);
		when(fakeALU.getPostALUSequenceNum(false)).thenReturn(-1);
		when(memory.getPostMEMSequenceNum(true)).thenReturn(0);
		when(memory.getPostMEMDestReg()).thenReturn(4);
		when(memory.getPostMEMOpResult()).thenReturn(444);
		alus.add(fakeALU);

		WriteBack writeBack = new WriteBack(memory, alus, registers);
		writeBack.processClockCycle();
		
		assertEquals(registers.getValue(4), 444);
	}
} // end class

