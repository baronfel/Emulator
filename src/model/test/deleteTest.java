package model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import model.MemoryAccess;
import model.Memory;
import java.util.Map;
import java.util.HashMap;


public class deleteTest {

  private static MemoryAccess testMEM;
  private static Memory memoryBanks;
  private static Map<String, Integer> cycles = new HashMap<String, Integer>(5);
  
  public static void main(String[] args){
    
	 cycles.put("lw", 2);
     cycles.put("sw", 3);
     
     memoryBanks = new Memory(100);
     Integer memAdd = new Integer(55);
     memoryBanks.setValueAt(4,memAdd);
     
     testMEM = new MemoryAccess( memoryBanks, 2, cycles);
     
     testMEM.addToPreMEM("lw", 100, 0, 5, 4, 2);
     testMEM.addToPreMEM("sw", 101, 4, 55, 4, 3);
     testMEM.processClockCycle();
     testMEM.processClockCycle();
     
 
     testMEM.processClockCycle();
     testMEM.processClockCycle();
     testMEM.processClockCycle();
     
     
     //System.out.println(memoryBanks.getValueAt(0));
     
     System.out.println(memoryBanks.getValueAt(4));
     System.out.println(memoryBanks.getValueAt(8));
 
     //assertEquals((int)memoryBanks.getValueAt(8), 55);
     
 
 }

}   //end class