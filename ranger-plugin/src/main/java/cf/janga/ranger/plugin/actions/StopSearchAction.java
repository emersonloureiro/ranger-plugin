package cf.janga.ranger.plugin.actions;

import org.eclipse.swt.widgets.Shell;

import cf.janga.ranger.plugin.RangerComponentFactory;


/**
 * A JFace action to stop the current search. It's encapsulated here so it can
 * be used in different GUI elements.
 * 
 * @author Emerson Loureiro
 * 
 */
public class StopSearchAction extends RangerAction {

	public StopSearchAction(Shell shell, RangerComponentFactory componentFactory) {
		super("Stop current search", "Stops the current search, displaying all results found so far", shell, componentFactory);
		this.setImageDescriptor(ActionUtils.getEclipseStopIcon());
	}

	/** {@inheritDoc} */
	@Override
	public void runImpl() {
		this.componentFactory.getSearchProcessor().stopCurrentSearch();
	}
}
