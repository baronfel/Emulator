/**
 * 
 */
package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import controller.RegisterController;
import interfaces.IModelListener;
import model.ModelEvent;
import org.eclipse.swt.widgets.Label;


/**
 * @author Chester
 *
 */
public class RegisterView implements IModelListener {

	private Label registerValue;
	private int watchedRegister;
	private RegisterController controller;
	
	public RegisterView(final Composite parent, RegisterController controller, int registerToWatch)
	{
		this.controller = controller;
		watchedRegister = registerToWatch;
		InitControls(parent);
		controller.addListener(watchedRegister, this);
	}
	
	private void InitControls(final Composite parent)
	{
		registerValue = new Label(parent, SWT.None);
		registerValue.setText(String.valueOf(controller.getRegisterValue(watchedRegister)));
	}
	
	@Override
	public void modelChanged(ModelEvent aEvent) {
		registerValue.setText(String.valueOf(aEvent.getExtraData()));
	}
}
