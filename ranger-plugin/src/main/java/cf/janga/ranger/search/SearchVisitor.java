package cf.janga.ranger.search;

import cf.janga.ranger.core.TrackingException;

/**
 * A visitor for processing different types of {@link Search} objects.
 * 
 * @author Emerson Loureiro
 * 
 */
public interface SearchVisitor {

	/**
	 * Visits the given search parameter.
	 * 
	 * @see ProjectSearch
	 * @throws TrackingException
	 */
	void visit(ProjectSearch projectSearch) throws TrackingException;

	/**
	 * Visits the given search parameter.
	 * 
	 * @see WorkspaceSearch
	 * @throws TrackingException
	 */
	void visit(WorkspaceSearch workspaceSearch) throws TrackingException;
}
