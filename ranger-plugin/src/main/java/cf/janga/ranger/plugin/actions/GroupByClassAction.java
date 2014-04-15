package cf.janga.ranger.plugin.actions;

import org.eclipse.swt.widgets.Shell;

import cf.janga.ranger.plugin.RangerComponentFactory;
import cf.janga.ranger.view.Groupers;
import cf.janga.ranger.view.SearchViewException;


/**
 * An action to group search results by the class where target methods have been
 * found.
 * 
 * @author Emerson Loureiro
 * 
 */
public class GroupByClassAction extends RangerAction {

	public GroupByClassAction(Shell shell, RangerComponentFactory componentFactory) {
		super("Group by class", "Group the results by class", shell, componentFactory, true);
		this.setImageDescriptor(ActionUtils.getEclipseClassIcon());
	}

	/** {@inheritDoc} */
	@Override
	public void runImpl() throws ActionException {
		try {
			this.componentFactory.getSearchResultViewController().changeGrouping(Groupers.CLASS_GROUPER);
		} catch (SearchViewException e) {
			throw new ActionException(e);
		}
	}
}
