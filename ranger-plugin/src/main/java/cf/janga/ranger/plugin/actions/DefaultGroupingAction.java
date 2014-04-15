package cf.janga.ranger.plugin.actions;

import org.eclipse.swt.widgets.Shell;

import cf.janga.ranger.plugin.RangerComponentFactory;
import cf.janga.ranger.view.Groupers;
import cf.janga.ranger.view.SearchViewException;


/**
 * Action to display search results ungrouped, which is the selected by default.
 * 
 * @author Emerson Loureiro
 * 
 */
public class DefaultGroupingAction extends RangerAction {

	public DefaultGroupingAction(Shell shell, RangerComponentFactory componentFactory) {
		super("Display all results", "Display the results ungrouped", shell, componentFactory, true);
		this.setImageDescriptor(ActionUtils.getEclipsePublicMethodIcon());
	}

	/** {@inheritDoc} */
	@Override
	public void runImpl() throws ActionException {
		try {
			this.componentFactory.getSearchResultViewController().changeGrouping(Groupers.DEFAULT_GROUPER);
		} catch (SearchViewException e) {
			throw new ActionException(e);
		}
	}
}
