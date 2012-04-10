/**
 * 
 */
package view;

import model.BenchmarkResult;
import model.Simulation;

import org.eclipse.swt.widgets.Composite;

import controller.ResultsController;

/**
 * @author Chet
 *
 */
public class ResultsView {

	protected ResultsController controller;

	public ResultsView(final Composite group, int i) {
		controller = new ResultsController();
	}
	
	public ResultsView(final Composite group, int i, BenchmarkResult result)
	{
		controller = new ResultsController(result);
	}

	public ResultsController getController() {
		return controller;
	}

}
