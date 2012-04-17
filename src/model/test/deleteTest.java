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
    
     cycles.put("sw", 3);
     
     memoryBanks = new Memory(100);
     Integer memAdd = new Integer(55);
     memoryBanks.setValueAt(4,memAdd);
     
     testMEM = new MemoryAccess( memoryBanks, 1, cycles);
     
     //addToPreMEM(String opName, int seq, int rs, int rt, int imm, int cycles)
     testMEM.addToPreMEM("sw", 101, 4, 55, 4, 3);
         
     testMEM.processClockCycle();
     testMEM.processClockCycle();
     testMEM.processClockCycle();
     testMEM.processClockCycle();
     
     //System.out.println(memoryBanks.getValueAt(0));
     
     System.out.println(memoryBanks.getValueAt(4));
     System.out.println(memoryBanks.getValueAt(8));
 
     assertEquals((int)memoryBanks.getValueAt(8), 55);
 
 }

}   //end class