package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import controller.RegisterController;

public class RegistryView {
	
	private RegisterController controller;
	
	public RegistryView(final Composite parent, RegisterController controller)
	{
		this.controller = controller;
		InitControls(parent);
	}
	
	private void InitControls(final Composite parent)
	{
		Group regGroup = new Group(parent, SWT.NONE);
		regGroup.setText("Registry");
		regGroup.setLayout(new GridLayout(8, false));
		
		for(int i = 0; i < 32; i++ )
		{
			RegisterView view = new RegisterView(parent, controller, i);
		}
	}
}
