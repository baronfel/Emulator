/**
 * 
 */
package view;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Chester
 *
 * This class is the entry point for the program.
 */
public class Presentation {

	private static String APPNAME = "MIPS Emulator";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		Display display = new Display();
		
		Shell shell = new Shell(display);
		shell.setText(APPNAME);
		shell.setLayout(new GridLayout(1, false));
		
		StartView2 beginView = new StartView2(shell);
		
		shell.pack();
		shell.open();
		while(!shell.isDisposed())
		{
			if(!display.readAndDispatch())
			{
				display.sleep();
			}
		}
		display.dispose();
	}

}
