/**
 * 
 */
package model.test;

import static org.junit.Assert.*;

import interfaces.IInstruction;

import java.util.ArrayList;
import java.util.List;



import model.FetchUnit;
import model.ITypeInstruction;
import model.Issue;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author Bob
 *
 */
public class FetchTest {
	private static List <IInstruction> ilist = new ArrayList<IInstruction>();
	private Issue issue = new Issue(null, null);
	private FetchUnit fetch = new FetchUnit(ilist, issue);
	
	@BeforeClass
	public static void GetEverything()
	{
		IInstruction instruction = new ITypeInstruction();
		ilist.add(instruction);
	}
	
	@Test
	public void FetchWorks() {
		assertEquals(issue.getNumInPreIssue(), 0);
		fetch.Cycle();
		assertEquals(issue.getNumInPreIssue(), 1);
	}

}
