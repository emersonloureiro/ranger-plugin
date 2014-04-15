package cf.janga.ranger.plugin.gui;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import cf.janga.ranger.plugin.actions.RangerAction;
import cf.janga.ranger.plugin.actions.StartSearchAction;
import cf.janga.ranger.plugin.integration.RangerComponentFactoryImpl;


/**
 * Action triggered when the user clicks on the popup menu item provided by the
 * plugin.
 * 
 * @author Emerson Loureiro
 * 
 */
public class StartSearchPopupMenuItem implements IObjectActionDelegate {

	private Shell shell;

	private RangerAction startSearchAction;

	/** Default constructor. */
	public StartSearchPopupMenuItem() {
		super();
		this.startSearchAction = new StartSearchAction(this.shell, RangerComponentFactoryImpl.getInstance());
	}

	/** {@inheritDoc} */
	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.shell = targetPart.getSite().getShell();
	}

	/** {@inheritDoc} */
	@Override
	public void run(IAction action) {
		this.startSearchAction.run();
	}

	/** {@inheritDoc} */
	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}
}
