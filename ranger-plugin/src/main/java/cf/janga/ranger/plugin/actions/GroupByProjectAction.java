package cf.janga.ranger.plugin.actions;

import org.eclipse.swt.widgets.Shell;

import cf.janga.ranger.plugin.RangerComponentFactory;
import cf.janga.ranger.view.Groupers;
import cf.janga.ranger.view.SearchViewException;


/**
 * An action to group search results by the project where target methods have
 * been found.
 * 
 * @author Emerson Loureiro
 * 
 */
public class GroupByProjectAction extends RangerAction {

	public GroupByProjectAction(Shell shell, RangerComponentFactory componentFactory) {
		super("Group by project", "Group the results by project", shell, componentFactory, true);
		this.setImageDescriptor(ActionUtils.getEclipseProjectIcon());
		this.setChecked(true);
	}

	/** {@inheritDoc} */
	@Override
	public void runImpl() throws ActionException {
		try {
			this.componentFactory.getSearchResultViewController().changeGrouping(Groupers.PROJECT_GROUPER);
		} catch (SearchViewException e) {
			throw new ActionException(e);
		}
	}
}
