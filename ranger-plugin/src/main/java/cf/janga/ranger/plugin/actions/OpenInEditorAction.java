package cf.janga.ranger.plugin.actions;

import org.eclipse.swt.widgets.Shell;

import cf.janga.ranger.plugin.RangerComponentFactory;
import cf.janga.ranger.view.SearchViewException;
import cf.janga.ranger.view.SearchViewNode;


/**
 * A JFace action to open a java element that's being displayed on the call
 * hierarchy view.
 * 
 * @author Emerson Loureiro
 * 
 */
public class OpenInEditorAction extends RangerAction {

	private SearchViewNode viewNode;

	public OpenInEditorAction(Shell shell, SearchViewNode viewNode, RangerComponentFactory componentFactory) {
		super("Open in editor", "Opens this element in editor", shell, componentFactory);
		this.viewNode = viewNode;
	}

	/** {@inheritDoc} */
	@Override
	public void runImpl() throws ActionException {
		try {
			this.viewNode.openInEditor();
		} catch (SearchViewException e) {
			throw new ActionException(e, e.getMessage());
		}
	}
}
