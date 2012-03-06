/**
 * 
 */

package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;


/**
 * @author Chet
 *
 * This class is the entry point for the app.  When this is run, the main UI is set up and shown to the user.
 */
public class StartView {

	private static String APPNAME = "MIPS Emulator";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// A display is an SWT session, multiple Screens can be hooked up to one.
		Display display = new Display();
		
		Shell shell = new Shell(display);
		shell.setText(APPNAME);
		shell.setLayout(new FillLayout());
		
		// Add a tab control
		// Tab 1
		ConfigurationView cView = new ConfigurationView(shell, 0);
		// Tab 2 (play/pause controls)
		// Tab 3 (reports!)
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
