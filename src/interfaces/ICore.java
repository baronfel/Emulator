/**
 * A core on a processor with components. It contains a list of components that the core has.
 */

package interfaces;

import java.util.ArrayList;
import java.util.List;

public interface ICore {
	
	/**
	 * This is a list of the ICoreComponents that make up this core.
	 * This list usually contains at least one of the following classes:
	 * 		IALU, IFetchUnit, IIssueUnit, IMemoryAccess, IWriteBack
	 */
	public List<ICoreComponent> _components = new ArrayList<ICoreComponent>();
	
}