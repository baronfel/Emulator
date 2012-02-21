/**
 * The core component that handles deciding which instruction should go through the pipeline next.
 * NO IT DOESN'T THAT'S WHAT FETCH DOES. WHAT DOES THIS DO?
 * @see ICoreComponent
 */

package model;

public interface IIssueUnit extends ICoreComponent {

	public void IssueInstructions();
}